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
     *
     */
    int cont = 0;
    int num1;
    int num2;

    //Expresiones regulares
    String palabras[] = {"SUMA", "RESTA", "MULTIPLICACION", "DIVISION", "INT", "IMPRIMIR"}; //conjunto de palabras reservadas
    boolean stopSystem = false; //Se usa en caso de que ocurra un error en la funcion sintaxis
    HashMap<String, Integer> vars = new HashMap<>();

    public void sintax(String pSentencia, boolean pActionSwitch) {
        boolean activeSwitch = pActionSwitch;
        byte result = -2;
        
        int value1 = 0;
        int value2 = 0;
        String mensaje = "",text = " ";
        String[] array = new String[3];

       // byte saveVar = 0;
        array = pSentencia.split(" ");

        for (byte i = 0; i < this.palabras.length; i++) {//Se recorre buscando la funcion
            if (array[0].matches(this.palabras[i])) {
                result = i;
                break;
            }
        }
        if (result >= 0) {
            if (result == 4) { //En caso de que sea una varible                                
                //saveVar = this.sintaxVariable(pSentencia);
                if(this.sintaxVariable(pSentencia) != 1){ //SI es diferente de 1 sigifica que hubo un error => (duplicidad,no existe,guarda letras)
                    return;
                }                               
            }//Fin Verificacion Variable
            else if (result == 5) {//Se verifica la funcion IMPRIMIR
                array = pSentencia.split(" ");
                System.out.println(array.length);
                text = this.Error.getText();
                for (int i = 1; i < array.length; i++) { //Se toma el mensaje
                    mensaje = mensaje + " " + array[i];
                }                
                return;
                
            }//Fin Imprimir
            
            //Como las funciones se van a trabajar con 2 valores por eso lo hice asi
            //Se procede a verificar si son numeros o variables
            value1 = getValue(array[1]);
            value2 = getValue(array[2]);
            
            if(value1 == 'n' || value2 =='n'){//Si alguno de los valores no es valido         
                activeSwitch = false;//Se desactiva el switch en caso de que la variable o numero no sea valido
                return;
            }else{   
                if(!activeSwitch){//Se activa el Swicth si la funcion es llamada desde el boton compilar
                this.Error.setText("Compilacion Exitosa");
                }
            }            
            
            if(activeSwitch){//Se activa el Swicth, ya que la funcion es llamada por "CompilaryEjecutar"
                fnSwitch(result,value1,value2,mensaje);                
            }
            
        } else {
            this.Error.setText("Error de Compilacion");
            this.stopSystem = true;
        }
    }
    public int getValue(String pVariable){
        boolean numeric = pVariable.matches("\\d*");
        if (numeric) {
                return Integer.parseInt(pVariable);

            } else {
                if (this.vars.containsKey(pVariable)) {
                    return this.vars.get(pVariable);
                }else{
                    this.Error.setText("Errror!!! La variable: "+pVariable+" no existe.");
                    this.stopSystem = true;
                }
            }
        return 'n';
    }

    public void fnSwitch(byte pPoss, int value1, int value2,String pMensaje) {
        String text = this.Error.getText();
            int resultado = 0;
        switch (pPoss) {
            case 0://Suma
               resultado = value1 + value2;
                this.Error.setText(text + "\n" + "El resultado de la suma es: " + resultado);
                 
                break;
            case 1://Resta
                    resultado = value1 - value2;
                this.Error.setText(text + "\n" + "El resultado de la resta es: " + resultado);
                break;
                
            case 2://Multiplicacion
                resultado = value1 * value2;
                this.Error.setText(text + "\n" + "El resultado de la multiplicacion es: " + resultado);
                break;
            case 3://Division
                resultado = value1 / value2;
                this.Error.setText(text + "\n" + "El resultado de la division es: " + resultado);
                break;
            case 5://Imprimir
                 this.Error.setText(text + "\n" + pMensaje);               
                break;
        }

    }

    public byte sintaxVariable(String pSentencia) {
        String array[]; //= new String[3];
        boolean insert = false;
        boolean numeric = false;
        array = pSentencia.split(" ");
        numeric = array[2].matches("\\d*");
        
        //Vertifica que el nombre de la variable no se encuentre en el arreglo de palabras reservadas
        for (byte j = 0; j < this.palabras.length; j++) {
            insert = this.palabras[j].equals(array[1]);//True == Encuentra coincidencias 
            if (insert) {//Si encuentra coincidencias se debe de salir
                this.Error.setText("Errror!!! El nombre: " + array[1] + " es una palabra reservada.");
                this.stopSystem = true;
                return 0;// No Permitido
            }
        }
        if (this.vars.containsKey(array[1])) {
            this.Error.setText("Errror!!! La variable: " + array[1] + " ya se encuentra declarada.");
            this.stopSystem = true;
            return 2;//Ya se encuentra registrada
        } else {
            if (numeric) {
                //Inserta la variable con el valor
                this.vars.put(array[1], Integer.parseInt(array[2]));
                return 1;
            } else {//No ingresa ya que la varibale contiene letras
                this.stopSystem = true;
                this.Error.setText("Errror!!! La Variable: " + array[1] + " solo admite numeros.");
                return 3;
            }
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
        vars.clear(); //limpiamos por si la persona quiere compilar varias veces
        this.stopSystem = false;
        StringTokenizer tokens = new StringTokenizer(texto, ";\n\r");
        String sentencia = "";
        

        while (tokens.hasMoreTokens()) {

            sentencia = tokens.nextToken();
            if(!stopSystem){
             sintax(sentencia, false);            
            }else
                return;

        }
    }//GEN-LAST:event_CompilarActionPerformed

    private void LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpiarActionPerformed
        txtATexto1.setText("");
        LineaError.setText("");
        Error.setText("");
    }//GEN-LAST:event_LimpiarActionPerformed

    private void compejecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compejecutarActionPerformed
        this.Error.setText(" ");
        this.stopSystem = false;
        vars.clear(); //limpiamos por si la persona quiere compilar y ejecutar varias veces
        String texto = this.txtATexto1.getText();
        StringTokenizer tokens = new StringTokenizer(texto, ";\n\r");
        String sentencia = "";        
        while (tokens.hasMoreTokens()) {
            sentencia = tokens.nextToken();
           if(!stopSystem){
             sintax(sentencia, true);//Como se ejecuta se manda  true para que se ejecuten las funciones      
            }else
                return;
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
