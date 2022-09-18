package presentacion;

import java.awt.Font;

import javax.swing.JInternalFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import logica.IcontroladorUsuario; 


import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.awt.event.ItemEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.JTextPane;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import java.awt.Color;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import datatypes.DtUsuario;
import datatypes.DtSocioExtra;
import datatypes.DtFechaHora;
import datatypes.DtProfesorExtra;

import javax.swing.tree.DefaultMutableTreeNode;

import excepciones.UsuarioNoExisteException;

@SuppressWarnings("serial")
public class ConsultaUsuario extends JInternalFrame {

	//Datos del caso de uso

	Set<String> usuarios;
	DtUsuario datosUsuarioActual;
	
	private IcontroladorUsuario controlUsr;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldEmail;
	private JTextField textFieldDia;
	private JTextField textFieldAnio;
	private JComboBox<String> comboBoxUsuario;
	private JLabel labelUsuario;
	private JTextArea textAreaBiografia;
	private JTextField textFieldWebsite;
	private JLabel labelWebsite;
	private JLabel labelBiografia;
	private JLabel labelDescripcion;
	private JLabel labelInstitucion;
	private JScrollPane scrollPane;
	private JTextArea textAreaDescripcion;
	private JScrollPane scrollPane_1;
	private JLabel labelAclaracionFecha;
	private JTextPane textPaneTipoDeUsuario;
	private JLabel lblNewLabel;
	private JTextField textFieldMes;
	private JTextField textFieldInstitucion;
	private JScrollPane scrollPaneTree;
	private JTree tree;
	private JLabel labelWebsite_1;
	private JLabel lblNewLabel_2;
	
	ConsultaDictadoClase refCDC;
	ConsultaActividadDeportiva refCAD;
	
	public ConsultaUsuario(IcontroladorUsuario IUC) {

		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(true);
		this.usuarios = new HashSet<>();
		this.datosUsuarioActual = null;
		this.controlUsr = IUC;
		
		/* 
		 *  Parametrizacion de dimensiones
		 */
		int columns = 8;
		int rows = 9;
		int iframeWidth = 480;
		int iframeHeight = 575;
		int gridWidth = iframeWidth/columns;
		int gridHeight = iframeHeight/rows;
		setBounds(100-gridWidth+gridWidth-gridHeight+gridHeight, 25, 487, 682); // w,h
		
		setTitle("Consulta de usuario");
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {30, 60, 60, 60, 60, 60, 60, 30};
		gridBagLayout.rowHeights = new int[]{25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 70, 25, 50, 25, 30, 25, 25, 100, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0};
		getContentPane().setLayout(gridBagLayout);
		
		
		comboBoxUsuario = new JComboBox<>();
		comboBoxUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		comboBoxUsuario.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				DefaultComboBoxModel<String> modeloUsuarios = new DefaultComboBoxModel<>();
				comboBoxUsuario.removeAllItems();
				modeloUsuarios.addElement("-");
				
				
				usuarios = controlUsr.obtenerUsuario();
				for(String us:usuarios) {
					modeloUsuarios.addElement(us);
				}
				comboBoxUsuario.setModel(modeloUsuarios);
				
			}
		});
		comboBoxUsuario.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				/*
				 * Habilita determinados campos segun el tipo de usuario.
				 * Ademas rellena cada campo con sus datos actuales
				 * Si no se seleccionan usuarios se limpian los campos
				 */	
				
				String tipoUsuario = "-";
				if(comboBoxUsuario.getSelectedIndex() > 0) {
					String nickUsuario = comboBoxUsuario.getItemAt(comboBoxUsuario.getSelectedIndex());
					try {
						datosUsuarioActual = controlUsr.seleccionarUsuario(nickUsuario);
					} catch (UsuarioNoExisteException ignore) { }
					textFieldNombre.setText(datosUsuarioActual.getNombre());
					textFieldApellido.setText(datosUsuarioActual.getApellido());
					textFieldEmail.setText(datosUsuarioActual.getEmail());
					DtFechaHora fechaNacimiento = datosUsuarioActual.getFechaNacimiento();
					textFieldDia.setText(String.valueOf(fechaNacimiento.getDia()));
					textFieldMes.setText(String.valueOf(fechaNacimiento.getMes()));
					textFieldAnio.setText(String.valueOf(fechaNacimiento.getAnio()));
					///lblNewLabel_2.setIcon(new ImageIcon(getClass().getResource("/users/"+nickUsuario+".jpg")));
					///lblNewLabel_2.setIcon(new ImageIcon(getClass().getResource("C:/Users/rippe/Desktop/foto/"+nickUsuario+".png")));


					String archivo = "C:\\Users\\User\\Desktop\\Github\\2022prog-app\\entrenamos.uy\\src\\img\\Usuarios\\"+nickUsuario+".png";
					
					try {
						BufferedImage bfi = ImageIO.read(new File (archivo));
						float aspectRatio = bfi.getWidth() / bfi.getHeight();
						Image imagenChiquita = bfi.getScaledInstance((int) (100), 100, Image.SCALE_DEFAULT);
						lblNewLabel_2.setIcon(new ImageIcon(imagenChiquita));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					//Image imagenChiquita = bfi.getScaledInstance((int) (100 * aspectRatio), 100, Image.SCALE_DEFAULT);
					//lblImagen.setIcon(new ImageIcon(imagenChiquita));

					
					
					//El usuario es profesor
					if(datosUsuarioActual instanceof DtProfesorExtra) {
						tipoUsuario = "Profesor";
						DtProfesorExtra datosProfesorActual = (DtProfesorExtra)datosUsuarioActual;
						textFieldInstitucion.setText(datosProfesorActual.getNombreInstitucion());
						textAreaDescripcion.setText(datosProfesorActual.getDescripcion());
						textAreaBiografia.setText(datosProfesorActual.getBiografia());
						textFieldWebsite.setText(datosProfesorActual.getLink());
						labelWebsite_1.setText("Clases dictadas (ordenadas por actividad deportiva)");
						Set<Entry<String, Set<String>>> m = datosProfesorActual.getClasesxActividades().entrySet();
						if(m.size()==0) {
							tree.setModel(new DefaultTreeModel(
									new DefaultMutableTreeNode("root") {
										{
											//WindowBuilder BUG: se cambia a getContentPane.add() por algun motivo. Dejarlo solo como add();
											add(new DefaultMutableTreeNode("El profesor no dicta ninguna clase."));
										}
									}
								));
						} else {
							tree.setModel(new DefaultTreeModel(
								new DefaultMutableTreeNode("root") {
									{
									
									for(Entry<String, Set<String>> ad: m) {
										DefaultMutableTreeNode nodoAct = new DefaultMutableTreeNode(ad.getKey());
										for(String c: ad.getValue()) {
											nodoAct.add(new DefaultMutableTreeNode(c));
										}
										add(nodoAct);
									}
									
									}
								}
							));
						}
					}
					else {
						labelWebsite_1.setText("Clases inscripto (ordenadas por actividad deportiva)");
						DtSocioExtra datosSocioActual = (DtSocioExtra)datosUsuarioActual;
						Set<Entry<String, Set<String>>> m = datosSocioActual.getAguadeUwu().entrySet();
						if(m.size()==0) {
							tree.setModel(new DefaultTreeModel(
									new DefaultMutableTreeNode("root") {
										{
											//WindowBuilder BUG: se cambia a getContentPane.add() por algun motivo. Dejarlo solo como add();
											add(new DefaultMutableTreeNode("El socio no está inscripto a ninguna clase."));
										}
									}
								));
						} else {
							tree.setModel(new DefaultTreeModel(
								new DefaultMutableTreeNode("root") {
									{
									
									for(Entry<String, Set<String>> ad: m) {
										DefaultMutableTreeNode nodoAct = new DefaultMutableTreeNode(ad.getKey());
										for(String c: ad.getValue()) {
											nodoAct.add(new DefaultMutableTreeNode(c));
										}
										add(nodoAct);
									}
									
									}
								}
							));
						}
						tipoUsuario = "Socio";
						/*
						 * Borro campos no relevantes para socio
						 */
						
						textFieldInstitucion.setText("");
						textAreaDescripcion.setText("");
						textAreaBiografia.setText("");
						textFieldWebsite.setText("");
//						textAreaActividades.setText("");
					}
					textPaneTipoDeUsuario.setText(tipoUsuario);
				}
				else {
					//clear();
				}
			}
		});
		
		labelUsuario = new JLabel("Usuario elegido");
		GridBagConstraints gbc_labelUsuario = new GridBagConstraints();
		gbc_labelUsuario.gridwidth = 2;
		gbc_labelUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_labelUsuario.anchor = GridBagConstraints.WEST;
		gbc_labelUsuario.gridx = 1;
		gbc_labelUsuario.gridy = 0;
		getContentPane().add(labelUsuario, gbc_labelUsuario);
		
		lblNewLabel = new JLabel("Tipo de usuario");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 4;
		gbc_lblNewLabel.gridy = 0;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		comboBoxUsuario.setModel(new DefaultComboBoxModel<>(new String[] {"-"}));
		GridBagConstraints gbc_comboBoxUsuario = new GridBagConstraints();
		gbc_comboBoxUsuario.gridwidth = 3;
		gbc_comboBoxUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxUsuario.gridx = 1;
		gbc_comboBoxUsuario.gridy = 1;
		getContentPane().add(comboBoxUsuario, gbc_comboBoxUsuario);
		
		textPaneTipoDeUsuario = new JTextPane();
		textPaneTipoDeUsuario.setEditable(false);
		GridBagConstraints gbc_textPaneTipoDeUsuario = new GridBagConstraints();
		gbc_textPaneTipoDeUsuario.gridwidth = 3;
		gbc_textPaneTipoDeUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_textPaneTipoDeUsuario.fill = GridBagConstraints.BOTH;
		gbc_textPaneTipoDeUsuario.gridx = 4;
		gbc_textPaneTipoDeUsuario.gridy = 1;
		Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
		textPaneTipoDeUsuario.setBorder(BorderFactory.createCompoundBorder(border, 
			      BorderFactory.createEmptyBorder(3, 4, 1, 0)));
		getContentPane().add(textPaneTipoDeUsuario, gbc_textPaneTipoDeUsuario);
		
		JLabel labelNombre = new JLabel("Nombre");
		GridBagConstraints gbc_labelNombre = new GridBagConstraints();
		gbc_labelNombre.gridwidth = 2;
		gbc_labelNombre.anchor = GridBagConstraints.SOUTH;
		gbc_labelNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_labelNombre.insets = new Insets(0, 0, 5, 5);
		gbc_labelNombre.gridx = 1;
		gbc_labelNombre.gridy = 2;
		getContentPane().add(labelNombre, gbc_labelNombre);
		
		JLabel labelApellido = new JLabel("Apellido");
		GridBagConstraints gbc_labelApellido = new GridBagConstraints();
		gbc_labelApellido.gridwidth = 2;
		gbc_labelApellido.anchor = GridBagConstraints.SOUTHWEST;
		gbc_labelApellido.insets = new Insets(0, 0, 5, 5);
		gbc_labelApellido.gridx = 3;
		gbc_labelApellido.gridy = 2;
		getContentPane().add(labelApellido, gbc_labelApellido);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setDisabledTextColor(Color.BLACK);
		textFieldNombre.setEnabled(false);
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.gridwidth = 2;
		gbc_textFieldNombre.fill = GridBagConstraints.BOTH;
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombre.gridx = 1;
		gbc_textFieldNombre.gridy = 3;
		getContentPane().add(textFieldNombre, gbc_textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setDisabledTextColor(Color.BLACK);
		textFieldApellido.setEnabled(false);
		GridBagConstraints gbc_textFieldApellido = new GridBagConstraints();
		gbc_textFieldApellido.gridwidth = 2;
		gbc_textFieldApellido.fill = GridBagConstraints.BOTH;
		gbc_textFieldApellido.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldApellido.gridx = 3;
		gbc_textFieldApellido.gridy = 3;
		getContentPane().add(textFieldApellido, gbc_textFieldApellido);
		textFieldApellido.setColumns(10);
		
		JLabel labelEmail = new JLabel("Correo electronico");
		GridBagConstraints gbc_labelEmail = new GridBagConstraints();
		gbc_labelEmail.gridwidth = 2;
		gbc_labelEmail.anchor = GridBagConstraints.SOUTH;
		gbc_labelEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_labelEmail.insets = new Insets(0, 0, 5, 5);
		gbc_labelEmail.gridx = 1;
		gbc_labelEmail.gridy = 4;
		getContentPane().add(labelEmail, gbc_labelEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setDisabledTextColor(Color.BLACK);
		textFieldEmail.setEnabled(false);
		GridBagConstraints gbc_textFieldEmail = new GridBagConstraints();
		gbc_textFieldEmail.gridwidth = 4;
		gbc_textFieldEmail.fill = GridBagConstraints.BOTH;
		gbc_textFieldEmail.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldEmail.gridx = 1;
		gbc_textFieldEmail.gridy = 5;
		getContentPane().add(textFieldEmail, gbc_textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JLabel labelFechaNacimiento = new JLabel("Fecha de nacimiento");
		GridBagConstraints gbc_labelFechaNacimiento = new GridBagConstraints();
		gbc_labelFechaNacimiento.gridwidth = 2;
		gbc_labelFechaNacimiento.anchor = GridBagConstraints.SOUTHWEST;
		gbc_labelFechaNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_labelFechaNacimiento.gridx = 1;
		gbc_labelFechaNacimiento.gridy = 6;
		getContentPane().add(labelFechaNacimiento, gbc_labelFechaNacimiento);
		
		labelAclaracionFecha = new JLabel("(dd/mm/aaaa)");
		GridBagConstraints gbc_labelAclaracionFecha = new GridBagConstraints();
		gbc_labelAclaracionFecha.anchor = GridBagConstraints.EAST;
		gbc_labelAclaracionFecha.gridwidth = 2;
		gbc_labelAclaracionFecha.insets = new Insets(0, 0, 5, 5);
		gbc_labelAclaracionFecha.gridx = 5;
		gbc_labelAclaracionFecha.gridy = 6;
		getContentPane().add(labelAclaracionFecha, gbc_labelAclaracionFecha);
		
		textFieldDia = new JTextField();
		textFieldDia.setDisabledTextColor(Color.BLACK);
		textFieldDia.setEnabled(false);
		GridBagConstraints gbc_textFieldDia = new GridBagConstraints();
		gbc_textFieldDia.gridwidth = 2;
		gbc_textFieldDia.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDia.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDia.gridx = 1;
		gbc_textFieldDia.gridy = 7;
		getContentPane().add(textFieldDia, gbc_textFieldDia);
		textFieldDia.setColumns(10);
		
		textFieldMes = new JTextField();
		textFieldMes.setDisabledTextColor(Color.BLACK);
		textFieldMes.setEnabled(false);
		textFieldMes.setColumns(10);
		GridBagConstraints gbc_textFieldMes = new GridBagConstraints();
		gbc_textFieldMes.gridwidth = 2;
		gbc_textFieldMes.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldMes.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldMes.gridx = 3;
		gbc_textFieldMes.gridy = 7;
		getContentPane().add(textFieldMes, gbc_textFieldMes);
		
		textFieldAnio = new JTextField();
		textFieldAnio.setDisabledTextColor(Color.BLACK);
		textFieldAnio.setEnabled(false);
		GridBagConstraints gbc_textFieldAnio = new GridBagConstraints();
		gbc_textFieldAnio.gridwidth = 2;
		gbc_textFieldAnio.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAnio.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAnio.gridx = 5;
		gbc_textFieldAnio.gridy = 7;
		getContentPane().add(textFieldAnio, gbc_textFieldAnio);
		textFieldAnio.setColumns(10);
		
		labelInstitucion = new JLabel("Nombre de Institucion");
		GridBagConstraints gbc_labelInstitucion = new GridBagConstraints();
		gbc_labelInstitucion.anchor = GridBagConstraints.WEST;
		gbc_labelInstitucion.gridwidth = 2;
		gbc_labelInstitucion.insets = new Insets(0, 0, 5, 5);
		gbc_labelInstitucion.gridx = 1;
		gbc_labelInstitucion.gridy = 8;
		getContentPane().add(labelInstitucion, gbc_labelInstitucion);
		
		textFieldInstitucion = new JTextField();
		textFieldInstitucion.setDisabledTextColor(Color.BLACK);
		textFieldInstitucion.setEnabled(false);
		textFieldInstitucion.setColumns(10);
		GridBagConstraints gbc_textFieldInstitucion = new GridBagConstraints();
		gbc_textFieldInstitucion.gridwidth = 6;
		gbc_textFieldInstitucion.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldInstitucion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldInstitucion.gridx = 1;
		gbc_textFieldInstitucion.gridy = 9;
		getContentPane().add(textFieldInstitucion, gbc_textFieldInstitucion);
		
		labelDescripcion = new JLabel("Descripcion");
		GridBagConstraints gbc_labelDescripcion = new GridBagConstraints();
		gbc_labelDescripcion.gridwidth = 2;
		gbc_labelDescripcion.anchor = GridBagConstraints.WEST;
		gbc_labelDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_labelDescripcion.gridx = 1;
		gbc_labelDescripcion.gridy = 10;
		getContentPane().add(labelDescripcion, gbc_labelDescripcion);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 6;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 11;
		getContentPane().add(scrollPane, gbc_scrollPane);
		
		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setDisabledTextColor(Color.BLACK);
		scrollPane.setViewportView(textAreaDescripcion);
		textAreaDescripcion.setEnabled(false);
		textAreaDescripcion.setLineWrap(true);
		textAreaDescripcion.setWrapStyleWord(true);
		
		labelBiografia = new JLabel("Biografia");
		GridBagConstraints gbc_labelBiografia = new GridBagConstraints();
		gbc_labelBiografia.gridwidth = 2;
		gbc_labelBiografia.anchor = GridBagConstraints.WEST;
		gbc_labelBiografia.insets = new Insets(0, 0, 5, 5);
		gbc_labelBiografia.gridx = 1;
		gbc_labelBiografia.gridy = 12;
		getContentPane().add(labelBiografia, gbc_labelBiografia);
		
		scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridheight = 2;
		gbc_scrollPane_1.gridwidth = 6;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 13;
		getContentPane().add(scrollPane_1, gbc_scrollPane_1);
		
		textAreaBiografia = new JTextArea();
		textAreaBiografia.setDisabledTextColor(Color.BLACK);
		scrollPane_1.setViewportView(textAreaBiografia);
		textAreaBiografia.setEnabled(false);
		textAreaBiografia.setLineWrap(true);
		textAreaBiografia.setWrapStyleWord(true);
		
		labelWebsite = new JLabel("Website");
		GridBagConstraints gbc_labelWebsite = new GridBagConstraints();
		gbc_labelWebsite.gridwidth = 2;
		gbc_labelWebsite.anchor = GridBagConstraints.WEST;
		gbc_labelWebsite.insets = new Insets(0, 0, 5, 5);
		gbc_labelWebsite.gridx = 1;
		gbc_labelWebsite.gridy = 15;
		getContentPane().add(labelWebsite, gbc_labelWebsite);
		
		textFieldWebsite = new JTextField();
		textFieldWebsite.setDisabledTextColor(Color.BLACK);
		textFieldWebsite.setEnabled(false);
		GridBagConstraints gbc_textFieldWebsite = new GridBagConstraints();
		gbc_textFieldWebsite.gridwidth = 6;
		gbc_textFieldWebsite.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldWebsite.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldWebsite.gridx = 1;
		gbc_textFieldWebsite.gridy = 16;
		getContentPane().add(textFieldWebsite, gbc_textFieldWebsite);
		textFieldWebsite.setColumns(10);
		
		labelWebsite_1 = new JLabel("Clases");
		GridBagConstraints gbc_labelWebsite_1 = new GridBagConstraints();
		gbc_labelWebsite_1.anchor = GridBagConstraints.WEST;
		gbc_labelWebsite_1.gridwidth = 6;
		gbc_labelWebsite_1.insets = new Insets(0, 0, 5, 5);
		gbc_labelWebsite_1.gridx = 1;
		gbc_labelWebsite_1.gridy = 17;
		getContentPane().add(labelWebsite_1, gbc_labelWebsite_1);
		
		scrollPaneTree = new JScrollPane();
		GridBagConstraints gbc_scrollPaneTree = new GridBagConstraints();
		gbc_scrollPaneTree.gridy = 18;
		gbc_scrollPaneTree.gridx = 1;
		gbc_scrollPaneTree.gridwidth = 6;
		gbc_scrollPaneTree.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridheight = 1;
		gbc_scrollPane.gridwidth = 6;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 18;
		getContentPane().add(scrollPaneTree, gbc_scrollPaneTree);
		
		lblNewLabel_2 = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.gridwidth = 3;
		gbc_lblNewLabel_3.gridheight = 3;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 5;
		gbc_lblNewLabel_3.gridy = 2;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_3);
		
		
		
		tree = new JTree();
		tree.setRootVisible(false);
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("root") {
				{
					//WindowBuilder BUG: se cambia a getContentPane.add() por algun motivo. Dejarlo solo como add();
					// Cada vez que se abre la ventan design hay que corregirlo xddd;
					add(new DefaultMutableTreeNode("Aqui se listan las clases."));
					add(new DefaultMutableTreeNode("Las clases estan organizadas por actividad deportiva."));
				}
			}
		));
		scrollPaneTree.setViewportView(tree);
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    if (SwingUtilities.isRightMouseButton(e)) {
			        TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
			        if(selPath==null) return;
			        if(selPath.getPathCount()==2 && (((DefaultMutableTreeNode) selPath.getPathComponent(1)).getChildCount())>0) {
			        	String q = (String) ((DefaultMutableTreeNode) selPath.getPathComponent(1)).getUserObject();
			        	//AQUI VA LA REFERENCIA A LA ACTIVIDAD DEPORTIVA
			        	//System.out.println("DEBUG: selection: "+q);
			        	refCAD.refEntry(q);
			        	
			        }
			        if(selPath.getPathCount()==3 && (((DefaultMutableTreeNode) selPath.getPathComponent(2)).getChildCount())==0) {
			        	String act = (String) ((DefaultMutableTreeNode) selPath.getPathComponent(1)).getUserObject();
			        	String cla = (String) ((DefaultMutableTreeNode) selPath.getPathComponent(2)).getUserObject();
			        	//AQUI VA LA REFERENCIA A LA CLASE SELECCIONADA
			        	//System.out.println("DEBUG: selection: "+act+" "+cla);
			        	refCDC.refEntry(act,cla);
			        }
			    }
			}
		});
		
		GridBagConstraints gbc_tree = new GridBagConstraints();
		gbc_tree.gridwidth = 6;
		gbc_tree.insets = new Insets(0, 0, 5, 5);
		gbc_tree.fill = GridBagConstraints.BOTH;
		gbc_tree.gridx = 1;
		gbc_tree.gridy = 18;
		//getContentPane().add(tree, gbc_tree);
		
		
		JLabel lblNewLabel_1 = new JLabel("Seleccione una clase o una actividad deportiva (click derecho) para obtener su info.");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 6;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 19;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
	}
	
	public void clear() {
		try {
	        textPaneTipoDeUsuario.setText("");
	        textFieldNombre.setText("");
	        textFieldApellido.setText("");
	        textFieldEmail.setText("");
	    	textFieldDia.setText("");
	    	textFieldMes.setText("");
	    	textFieldAnio.setText("");
	    	textFieldInstitucion.setText("");
	    	textFieldWebsite.setText("");
	    	textAreaDescripcion.setText("");
	    	textAreaBiografia.setText("");
	    	labelWebsite_1.setText("Clases");
			tree.setModel(new DefaultTreeModel(
					new DefaultMutableTreeNode("root") {
						{
							
								//WindowBuilder BUG: se cambia a getContentPane.add() por algun motivo. Dejarlo solo como add();
								// Cada vez que se abre la ventan design hay que corregirlo xddd;
								add(new DefaultMutableTreeNode("Aqui se listan las clases."));
								add(new DefaultMutableTreeNode("Las clases estan organizadas por actividad deportiva."));
							
						}
					}
				));
	    	comboBoxUsuario.setSelectedIndex(0);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), getTitle(), JOptionPane.ERROR_MESSAGE);
		}
	}


	public void setRef(ConsultaDictadoClase consultaClass, ConsultaActividadDeportiva consActDep) {
		refCDC = consultaClass;
		refCAD = consActDep;
	}

}
