package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import vista.VentanaVideo;
import modelo.ModeloVideo;
import vista.Consulta;

public class ControladorVideo implements ActionListener{
    
    //Declaro objeto vista
    private VentanaVideo vistaVentanaVideo;
    Consulta VistaConsulta = new Consulta();
    
    //Declaro objeto modelo
    private ModeloVideo modelo = new ModeloVideo();
    
    //Declaro botón que está en la vista ingresa el dato
    public enum Accion{
        btGrabar,
        btnMostrar,
        btBuscar,
        btModificar,
        btEliminar,
        Consulta,
        Volver
        
    
    }
    //Constructor 
    public ControladorVideo (VentanaVideo vistaVentanaVideo){
        this.vistaVentanaVideo = vistaVentanaVideo;
        
    }
    
    public void iniciar(){
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(vistaVentanaVideo);
            this.vistaVentanaVideo.setLocationRelativeTo(null);
            this.vistaVentanaVideo.setTitle("VideoBuster");
            this.vistaVentanaVideo.setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {}
          catch (ClassNotFoundException ex) {}
          catch (InstantiationException ex) {}
          catch (IllegalAccessException ex) {}
        
        //Escuchamos los botones de todas partes
        this.vistaVentanaVideo.btGrabar.setActionCommand( "btGrabar" );
        this.vistaVentanaVideo.btGrabar.addActionListener(this);
        this.vistaVentanaVideo.btMostrar.setActionCommand( "btnMostrar" );
        this.vistaVentanaVideo.btMostrar.addActionListener(this);
        this.vistaVentanaVideo.btModificar.setActionCommand( "btModificar" );
        this.vistaVentanaVideo.btModificar.addActionListener(this);
        this.vistaVentanaVideo.Consulta.setActionCommand( "Consulta" );
        this.vistaVentanaVideo.Consulta.addActionListener(this);
         this.VistaConsulta.Volver.setActionCommand( "Volver" );
        this.VistaConsulta.Volver.addActionListener(this);
          this.vistaVentanaVideo.btEliminar.setActionCommand( "btEliminar" );
        this.vistaVentanaVideo.btEliminar.addActionListener(this);
        
        }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    switch (Accion.valueOf(e.getActionCommand())){  
            case btGrabar:
                if(this.modelo.agregaDato(Integer.parseInt(this.vistaVentanaVideo.txtCodigo.getText()),
                        this.vistaVentanaVideo.txtNombre.getText(),
                        this.vistaVentanaVideo.cmbCategoria.getSelectedItem().toString(),
                        this.vistaVentanaVideo.txtFormato.getText(),
                        Integer.parseInt(this.vistaVentanaVideo.txtPrecio.getText()))){
                        JOptionPane.showMessageDialog(null, "El dato fue agregado");
                    //Limpiamos textField
                    this.vistaVentanaVideo.txtCodigo.setText("");
                    this.vistaVentanaVideo.txtNombre.setText("");
                    this.vistaVentanaVideo.cmbCategoria.setSelectedIndex(0);
                    this.vistaVentanaVideo.txtNombre.setText("");
                   


                }else{
                    JOptionPane.showMessageDialog(null, "No se pudo agregar");}
                    break;
                    
              case btnMostrar:
                this.vistaVentanaVideo.setLocationRelativeTo(null);
                this.vistaVentanaVideo.setTitle("VideoBuster");
                this.vistaVentanaVideo.setVisible(true);
                this.vistaVentanaVideo.tableVideo.setModel(this.modelo.ListadoProducto());
                break;
                
              case Consulta:
                  this.VistaConsulta.setVisible(true);
                  this.vistaVentanaVideo.setVisible(false);
                  break;
                  
                  case Volver:
                      this.VistaConsulta.setVisible(false);
                  this.vistaVentanaVideo.setVisible(true);
                        
                    
                  break;
                  
                  case btEliminar:
                    int codigo1;
                    codigo1 = Integer.parseInt(this.vistaVentanaVideo.txtCodigo.getText());
                    this.modelo.eliminarProducto(codigo1);
                    break;
                    
              case btModificar:
                                  if(this.modelo.modificaProducto(Integer.parseInt(this.vistaVentanaVideo.txtCodigo.getText()),
                        this.vistaVentanaVideo.txtNombre.getText(),
                        this.vistaVentanaVideo.cmbCategoria.getSelectedItem().toString(),
                        this.vistaVentanaVideo.txtFormato.getText(),
                        Integer.parseInt(this.vistaVentanaVideo.txtPrecio.getText()))){
                        JOptionPane.showMessageDialog(null, "El dato fue agregado");
                    //Limpiamos textField
                    this.vistaVentanaVideo.txtCodigo.setText("");
                    this.vistaVentanaVideo.txtNombre.setText("");
                    this.vistaVentanaVideo.cmbCategoria.setSelectedIndex(0);
                    this.vistaVentanaVideo.txtNombre.setText("");
                    break;
                    
                    
                
                    
                    
                
                }
    }
}
}

