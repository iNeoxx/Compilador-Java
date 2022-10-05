/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.una.proyecto1;

import java.lang.reflect.Array;
import java.util.StringTokenizer;
import java.util.HashMap;

/**
 *
 * @author molin
 */
public class Compilador extends javax.swing.JFrame {

    /**
     
     */
       int cont = 0;
       int num1; 
       int num2;
       //Expresiones regulares
       String palabras[] = {"SUMA","RESTA","MULTIPLICACION","DIVISION", "INT", "IMPRIMIR"}; //conjunto de palabras reservadas
       String numberDel = "*\\s.*\\d.*\\s.*\\d";  //Expresion regular para poner numeros
       String varDel = "*\\s.*\\w*\\s*\\d"; //expresion regular para poner variables
       String letterDel = "*\\s.*\\w";
       String singleNum = "*\\s.*\\d";
       HashMap<String, Integer> vars = new HashMap<>();
       
       public byte sintax(String pSentencia){
           StringTokenizer tokens = new StringTokenizer(pSentencia, ";\n\r"); //no c pq se hace esto de nuevo porque creo que ya se hizo, pero no pasa nada si se queda
           byte result = -2; 
           
           String sentencia = tokens.nextToken();
           String error = " ";
           if(sentencia.matches(this.palabras[0]+this.numberDel)){ //Suma
               return result = 0;
           } else if(sentencia.matches(this.palabras[1]+this.numberDel)){ //Resta
               return result = 1;
           } else if(sentencia.matches(this.palabras[2]+this.numberDel)){ //Multiplicacion
               return result = 2;
           } else if(sentencia.matches(this.palabras[3]+this.numberDel)){ //Division
               return result = 3;
           } else if(sentencia.matches(this.palabras[4]+this.varDel)){ //Declaracion de variables
               return result = 4;
           } else if(sentencia.matches(this.palabras[5]+this.letterDel)){ //imprimir
               return result = 5;
           }
           //Aqui vamos a jugar con las funciones usando variables previamente almacenadas; 
           //entonces, las posibilidades son PALABRARESERVADA varaible numero, PALABRARESERVADA numero variable.
           else if(sentencia.matches(this.palabras[0]+this.letterDel+this.singleNum)){ //SUMA var num
               return result = 6;
           } else if(sentencia.matches(this.palabras[0]+this.singleNum+this.letterDel)){ //SUMA num var
               return result = 7; 
           } else if(sentencia.matches(this.palabras[0] + this.letterDel + this.letterDel)){ //SUMA var var
               return result = 8;
           } else if(sentencia.matches(this.palabras[1]+this.letterDel+this.singleNum)){ //RESTA var num
               return result = 9;
           } else if(sentencia.matches(this.palabras[1]+this.singleNum+this.letterDel)){ //RESTA num var
               return result = 10; 
           } else if(sentencia.matches(this.palabras[1] + this.letterDel + this.letterDel)){ //RESTA var var
               return result = 11;
           }else if(sentencia.matches(this.palabras[2]+this.letterDel+this.singleNum)){ //MULTIPLICACION var num
               return result = 12;
           } else if(sentencia.matches(this.palabras[2]+this.singleNum+this.letterDel)){ //MULTIPLICACION num var
               return result = 13; 
           } else if(sentencia.matches(this.palabras[2] + this.letterDel + this.letterDel)){ //MULTIPLICACION var var
               return result = 14;
           }else if(sentencia.matches(this.palabras[3]+this.letterDel+this.singleNum)){ //DIVISION var num
               return result = 15;
           } else if(sentencia.matches(this.palabras[3]+this.singleNum+this.letterDel)){ //DIVISION num var
               return result = 16; 
           } else if(sentencia.matches(this.palabras[3] + this.letterDel + this.letterDel)){ //DIVISION var var
               return result = 17;
           }
           error = sentencia;
           if(result < 0){
               this.Error.setText("Error de sintaxis. "+ error + " no es valido");
           }
           return result; 
       }
       
       public void switchFun(int pPoss, String sentencia){
           String array[]; //arreglo de 20 (espero que sea suficiente) para almacenar las partes del token y poder dividir palabras claves de variables o numeros
           int resultado;
           String text = this.Error.getText();
           switch(pPoss){ //Dependiendo del numero que devuelve la funcion de sintaxis, va a entrar a uno uu otro sqitch
               case 0: //Suma
                   array = sentencia.split(" "); //Aqui dividimos el token para que la palabra quede separada de los numeros
                   resultado = Integer.parseInt(array[1]) + Integer.parseInt(array[2]);
                   this.Error.setText(text + "\n" + "El resultado de la suma es: "+resultado);
                   break;
               case 1: //Resta
                   array = sentencia.split(" ");
                   resultado = Integer.parseInt(array[1]) - Integer.parseInt(array[2]);
                   this.Error.setText(text + "\n" + "El resultado de la resta es: "+resultado);
                   break; 
               case 2: //Multiplicacion
                   array = sentencia.split(" ");
                   resultado = Integer.parseInt(array[1]) * Integer.parseInt(array[2]);
                   this.Error.setText(text + "\n" + "El resultado de la multiplicacion es: "+resultado);
                   break;
               case 3: //Division
                   array = sentencia.split(" ");
                   resultado = Integer.parseInt(array[1]) / Integer.parseInt(array[2]);
                   this.Error.setText(text + "\n" + "El resultado de la division es: "+resultado);
                   break;
               case 4: //Declaracion de variables (proximo a programar)
                   //Se supone que las variables vienen como el siguiente ejemplo: INT nombe 23;
                   //entonces se sabe que array[1] es el nombre de la variable y array[2] el valor, entonces hay que buscar array[1] en los
                   //indices del hashmap para ver si la variable ya esta declarada, y si no, ingresarla
                   array = sentencia.split(" ");
                   if(vars.containsKey(array[1])){ //la variable ya esta
                       this.Error.setText("La variable "+array[1]+" ya esta declarada");
                       return;
                   }else{ //la variable no esta en el arreglo
                       vars.put(array[1], Integer.parseInt(array[2]));
                   }
                   break;
               case 5: //Imprimir
                   array = sentencia.split(" ");
                   System.out.println(array.length);
                   String mensaje = ""; 
                   for(int i = 1; i < array.length; i++){
                       mensaje = mensaje + " " +array[i];
                   }
                   this.Error.setText(text + "\n" + mensaje);
                   break;
               case 6: //SUMA var num
                   //Sabemos que ya viene con una estructura, entonces se debe busar la variable en el hasmap para ver si esta definida y sacar el valor
                   array = sentencia.split(" ");
                   if(vars.containsKey(array[1])){ //significa que la variable si esta
                       resultado = Integer.parseInt(array[2]) + vars.get(array[1]);
                       this.Error.setText(text + "\n" + "El resultado de la suma es: "+resultado);
                   } else{
                       this.Error.setText("La variable "+array[1]+" no esta declarada");
                   }
                   break;
               case 7:
                   break; 
               case 8:
                   break; 
               case 9: 
                   break;
               case 10:
                   break; 
               case 11: 
                   break; 
               case 12: 
                   break; 
               case 13: 
                   break; 
               case 14: 
                   break; 
               case 15: 
                   break; 
               case 16: 
                   break;
               case 17: 
                   break;
           }
       }
       
    public Compilador() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        txtATexto1 = new javax.swing.JEditorPane();
        Lineas = new javax.swing.JEditorPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        Error = new javax.swing.JEditorPane();
        Compilar = new javax.swing.JButton();
        Limpiar = new javax.swing.JButton();
        compejecutar = new javax.swing.JButton();
        LineaError = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtATexto1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtATexto1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtATexto1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtATexto1KeyReleased(evt);
            }
        });

        Lineas.setEditable(false);
        Lineas.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        Lineas.setText("1");
        Lineas.setOpaque(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(Lineas, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtATexto1, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtATexto1, javax.swing.GroupLayout.PREFERRED_SIZE, 3000, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Lineas, javax.swing.GroupLayout.PREFERRED_SIZE, 3000, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel3);

        Error.setEditable(false);
        Error.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        Error.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        Error.setForeground(java.awt.Color.blue);
        jScrollPane5.setViewportView(Error);

        Compilar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Compilar.setText("Compilar");
        Compilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CompilarActionPerformed(evt);
            }
        });

        Limpiar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Limpiar.setText("Limpiar");
        Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LimpiarActionPerformed(evt);
            }
        });

        compejecutar.setBackground(new java.awt.Color(204, 204, 204));
        compejecutar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        compejecutar.setText("Compilar y Ejecutar");
        compejecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compejecutarActionPerformed(evt);
            }
        });

        LineaError.setEditable(false);
        LineaError.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        LineaError.setForeground(java.awt.Color.red);
        LineaError.setToolTipText("");
        LineaError.setOpaque(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(LineaError, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(Compilar)
                                .addGap(72, 72, 72)
                                .addComponent(Limpiar)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(compejecutar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(37, 37, 37))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
                        .addGap(130, 130, 130))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LineaError)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Compilar)
                                    .addComponent(Limpiar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(compejecutar)))
                        .addGap(0, 74, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtATexto1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtATexto1KeyPressed
        StringTokenizer st = new StringTokenizer(txtATexto1.getText(), "\n", true);
        String txt = "", token;
        LineaError.setText("");
        Error.setText("");
        cont = 1;

        while (st.hasMoreTokens()) {
            token = st.nextToken();
            if ("\n".equals(token)) {
                cont++;
            }
        }

        for (int i = 1; i <= cont; i++) {
            txt += i + "\n";
        }
        Lineas.setText(txt);
    }//GEN-LAST:event_txtATexto1KeyPressed

    private void txtATexto1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtATexto1KeyReleased
        StringTokenizer st = new StringTokenizer(txtATexto1.getText(), "\n", true);
        String txt = "", token;
        cont = 1;

        while (st.hasMoreTokens()) {
            token = st.nextToken();
            if ("\n".equals(token)) {
                cont++;
            }
        }

        for (int i = 1; i <= cont; i++) {
            txt += i + "\n";
        }
        Lineas.setText(txt);
    }//GEN-LAST:event_txtATexto1KeyReleased

    private void CompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CompilarActionPerformed
        String texto = this.txtATexto1.getText();
        this.Error.setText(" ");
        StringTokenizer tokens = new StringTokenizer(texto, ";\n\r");
        String sentencia = tokens.nextToken();
        byte i = 0;
        while (tokens.hasMoreTokens()) {
            sentencia = tokens.nextToken(); 
            i = this.sintax(sentencia);
            if(i>=0){
                Error.setText("Compilado Exitosamente!");
            }else{
                System.out.println("Fallo en el compilar XD");
                return;
            }
        }
    }//GEN-LAST:event_CompilarActionPerformed

    private void LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpiarActionPerformed
        txtATexto1.setText("");
        LineaError.setText("");
        Error.setText("");
    }//GEN-LAST:event_LimpiarActionPerformed

    private void compejecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compejecutarActionPerformed
        vars.clear(); //limpiamos por si la persona quiere compilar y ejecutar varias veces
        String texto = this.txtATexto1.getText();
        StringTokenizer tokens = new StringTokenizer(texto, ";\n\r");
        String sentencia = "";
        byte i = 0;
        while(tokens.hasMoreTokens()){
            sentencia = tokens.nextToken();
            i = this.sintax(sentencia);
            if(i >= 0){
                this.switchFun(i, sentencia);
            } else{
                return;
            }
        }
    }//GEN-LAST:event_compejecutarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Compilador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton Compilar;
    private javax.swing.JEditorPane Error;
    private javax.swing.JButton Limpiar;
    private javax.swing.JEditorPane LineaError;
    private javax.swing.JEditorPane Lineas;
    private javax.swing.JButton compejecutar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JEditorPane txtATexto1;
    // End of variables declaration//GEN-END:variables
}
