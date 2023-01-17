package com.ogutcenali.controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Component;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.ogutcenali.dao.StudentDao;
import com.ogutcenali.entity.Contact;
import com.ogutcenali.entity.Student;
import com.ogutcenali.service.ContactService;
import com.ogutcenali.service.StudentService;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ImageIcon;
import java.awt.Choice;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;

public class StudentsApp {

	private JFrame frmStudentInformationSystem;
	private JTextField tf_id;
	private JTextField tf_email;
	private JTextField tf_firstname;
	private JTextField tf_lastname;
	private JTextField tf_phone2;
	private JTextField tf_phone1;
	private JComboBox cb_gender;
	private JTable table;
	private JEditorPane addres1;
	private JEditorPane addres2;

	private StudentDao studentDao = new StudentDao();
	private ContactService contactService = new ContactService();
	private StudentService studentService = new StudentService();

	private JButton searchLastname;
	private JPanel panel_1;
	private JLabel lbl_phone;
	private JLabel lbl_adres;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JLabel lbl_contactinfo;
	private JLabel lbl_contactinfo2;
	private JPanel panel_2;
	private JScrollPane scrollPane_2;
	private JButton btn_delete;
	private JButton btn_update;
	private JButton btn_save;
	private JLabel lbl_id;
	private JLabel lbl_fname;
	private JLabel lbl_gender;
	private JLabel lbl_email;
	private JLabel lbl_lname;
	private JButton searchEmail;
	private JButton searchFirstName;
	JButton btn_fotoekle;
	private JButton btn_getall;
	private JLabel lbl_language;
	private JComboBox comboBox_language;
	private JButton btnNewButton;
	private JLabel lbl_foto;
	private String imagePath = " ";
	private byte[] image;
	ImageIcon myimage;
	private JLabel lbl_foto2;

	private String imagePaths = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentsApp window = new StudentsApp();
					window.frmStudentInformationSystem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StudentsApp() {
		initialize();

		Locale.setDefault(new Locale("en", "EN"));
		// i18n();

	}

	public ImageIcon seticon(String m, byte[] image) {

		if (m != null) {

			myimage = new ImageIcon(m);
		} else {
			myimage = new ImageIcon(image);
		}

		Image img1 = myimage.getImage();
		Image img2 = img1.getScaledInstance(lbl_foto.getWidth(), lbl_foto.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon i = new ImageIcon(img2);
		return i;
	}

	private void i18n() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("com/ogutcenali/config/resource_bundle");
		lbl_fname.setText(resourceBundle.getString("word1"));
		lbl_lname.setText(resourceBundle.getString("word2"));
		lbl_email.setText(resourceBundle.getString("word3"));
		lbl_gender.setText(resourceBundle.getString("word4"));
		searchEmail.setText(resourceBundle.getString("word5"));
		searchFirstName.setText(resourceBundle.getString("word6"));
		searchLastname.setText(resourceBundle.getString("word7"));
		lbl_phone.setText(resourceBundle.getString("word8"));
		lbl_adres.setText(resourceBundle.getString("word9"));
		lbl_contactinfo.setText(resourceBundle.getString("word10"));
		lbl_contactinfo2.setText(resourceBundle.getString("word11"));
		btn_getall.setText(resourceBundle.getString("word12"));
		btn_save.setText(resourceBundle.getString("word13"));
		btn_update.setText(resourceBundle.getString("word14"));
		btn_delete.setText(resourceBundle.getString("word15"));
		lbl_language.setText(resourceBundle.getString("word16"));
		cb_gender.setModel(new DefaultComboBoxModel(
				new String[] { resourceBundle.getString("word17"), resourceBundle.getString("word18") }));
		comboBox_language.setModel(new DefaultComboBoxModel(new String[] { resourceBundle.getString("word19"),
				resourceBundle.getString("word20"), resourceBundle.getString("word21") }));
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", resourceBundle.getString("word3"),
				resourceBundle.getString("word1"), resourceBundle.getString("word2") }));
		frmStudentInformationSystem.setTitle(resourceBundle.getString("word22"));
		btn_fotoekle.setText(resourceBundle.getString("word23"));
	}

//	private void

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmStudentInformationSystem = new JFrame();
		frmStudentInformationSystem.setBackground(Color.BLACK);
		frmStudentInformationSystem.getContentPane().setBackground(new Color(40, 40, 40));
		frmStudentInformationSystem.setResizable(false);
		frmStudentInformationSystem.setTitle("Student Information System");
		frmStudentInformationSystem.setBounds(100, 100, 1183, 597);
		frmStudentInformationSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStudentInformationSystem.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBackground(new Color(41, 41, 41));
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(22, 41, 436, 245);
		frmStudentInformationSystem.getContentPane().add(panel);
		panel.setLayout(null);

		lbl_id = new JLabel("ID=");
		lbl_id.setForeground(Color.WHITE);
		lbl_id.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_id.setBounds(10, 44, 46, 14);
		panel.add(lbl_id);

		tf_id = new JTextField();
		tf_id.setEditable(false);
		tf_id.setBounds(99, 41, 172, 20);
		panel.add(tf_id);
		tf_id.setColumns(10);

		tf_email = new JTextField();
		tf_email.setBounds(99, 84, 172, 20);
		panel.add(tf_email);
		tf_email.setColumns(10);

		lbl_fname = new JLabel("First name=");
		lbl_fname.setForeground(Color.WHITE);
		lbl_fname.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_fname.setBounds(10, 126, 81, 14);
		panel.add(lbl_fname);

		lbl_gender = new JLabel("Gender=");
		lbl_gender.setForeground(Color.WHITE);
		lbl_gender.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_gender.setBounds(10, 209, 81, 14);
		panel.add(lbl_gender);

		lbl_email = new JLabel("e-mail=");
		lbl_email.setForeground(Color.WHITE);
		lbl_email.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_email.setBounds(10, 86, 79, 14);
		panel.add(lbl_email);

		lbl_lname = new JLabel("Last name=");
		lbl_lname.setForeground(Color.WHITE);
		lbl_lname.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_lname.setBounds(10, 164, 81, 14);
		panel.add(lbl_lname);

		tf_firstname = new JTextField();
		tf_firstname.setBounds(99, 123, 172, 20);
		panel.add(tf_firstname);
		tf_firstname.setColumns(10);

		tf_lastname = new JTextField();
		tf_lastname.setBounds(99, 161, 172, 20);
		panel.add(tf_lastname);
		tf_lastname.setColumns(10);

		cb_gender = new JComboBox();
		cb_gender.setModel(new DefaultComboBoxModel(new String[] { "MAN", "WOMAN" }));
		cb_gender.setBounds(99, 205, 96, 22);
		panel.add(cb_gender);

		searchEmail = new JButton("Search E-Mail");
		searchEmail.setBackground(Color.WHITE);
		searchEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				findbyEmail(tf_email.getText());

			}
		});
		searchEmail.setBounds(291, 82, 135, 23);
		panel.add(searchEmail);

		searchFirstName = new JButton("Search First Name");
		searchFirstName.setBackground(Color.WHITE);
		searchFirstName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				searchStudentwithFirstName(tf_firstname.getText());
			}
		});
		searchFirstName.setBounds(291, 122, 135, 23);
		panel.add(searchFirstName);

		searchLastname = new JButton("Search Last Name");
		searchLastname.setBackground(Color.WHITE);
		searchLastname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				searchStudentwithLastName(tf_lastname.getText());
			}
		});
		searchLastname.setBounds(291, 160, 135, 23);
		panel.add(searchLastname);

		btnNewButton = new JButton("X");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeTextField();
			}
		});
		btnNewButton.setBounds(340, 209, 59, 23);
		panel.add(btnNewButton);

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(40, 40, 40));
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(462, 41, 386, 245);
		frmStudentInformationSystem.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		lbl_phone = new JLabel("Phone= ");
		lbl_phone.setForeground(Color.WHITE);
		lbl_phone.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbl_phone.setBounds(6, 64, 70, 14);
		panel_1.add(lbl_phone);

		tf_phone2 = new JTextField();
		tf_phone2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String phoneNumber = tf_phone2.getText();
				int length = phoneNumber.length();
				char c = e.getKeyChar();

				if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {

					if (length < 10) {

						tf_phone2.setEditable(true);
					} else {
						tf_phone2.setEditable(false);

					}

				} else {

					if (e.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE
							|| e.getExtendedKeyCode() == KeyEvent.VK_DELETE) {
						tf_phone2.setEditable(true);
					} else {
						tf_phone2.setEditable(false);
						JOptionPane.showMessageDialog(null, "Please 0-9");
						tf_phone2.setEditable(true);
					}
				}
			}
		});
		tf_phone2.setColumns(10);
		tf_phone2.setBounds(230, 61, 135, 20);
		panel_1.add(tf_phone2);

		tf_phone1 = new JTextField();
		tf_phone1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				String phoneNumber = tf_phone1.getText();
				int length = phoneNumber.length();
				char c = e.getKeyChar();

				if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {

					if (length < 10) {

						tf_phone1.setEditable(true);
					} else {
						tf_phone1.setEditable(false);

					}

				} else {

					if (e.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE
							|| e.getExtendedKeyCode() == KeyEvent.VK_DELETE) {
						tf_phone1.setEditable(true);
					} else {
						tf_phone1.setEditable(false);
						JOptionPane.showMessageDialog(null, "Please 0-9");
						tf_phone1.setEditable(true);
					}
				}

			}
		});
		tf_phone1.setColumns(10);
		tf_phone1.setBounds(71, 61, 135, 20);
		panel_1.add(tf_phone1);

		lbl_adres = new JLabel("Addres=");
		lbl_adres.setForeground(Color.WHITE);
		lbl_adres.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbl_adres.setBounds(6, 119, 70, 14);
		panel_1.add(lbl_adres);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(71, 112, 135, 106);
		panel_1.add(scrollPane);

		addres1 = new JEditorPane();
		scrollPane.setViewportView(addres1);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(230, 114, 133, 104);
		panel_1.add(scrollPane_1);

		addres2 = new JEditorPane();
		scrollPane_1.setViewportView(addres2);

		lbl_contactinfo = new JLabel("Contact Info1");
		lbl_contactinfo.setForeground(Color.WHITE);
		lbl_contactinfo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_contactinfo.setBounds(99, 36, 95, 14);
		panel_1.add(lbl_contactinfo);

		lbl_contactinfo2 = new JLabel("Contact Info2");
		lbl_contactinfo2.setForeground(Color.WHITE);
		lbl_contactinfo2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_contactinfo2.setBounds(255, 36, 110, 14);
		panel_1.add(lbl_contactinfo2);

		panel_2 = new JPanel();
		panel_2.setBackground(new Color(41, 41, 41));
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(865, 41, 274, 245);
		frmStudentInformationSystem.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		lbl_foto = new JLabel("");

		lbl_foto.setBounds(32, 11, 232, 223);
		panel_2.add(lbl_foto);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBackground(new Color(255, 255, 255));
		scrollPane_2.setBounds(22, 297, 992, 244);
		frmStudentInformationSystem.getContentPane().add(scrollPane_2);

		table = new JTable();
		table.setBackground(new Color(255, 255, 255));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectRow = table.getSelectedRow();
				TableModel model = table.getModel();
				int value = Integer.parseInt(model.getValueAt(selectRow, 0).toString());
				studentInformation(value);

			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "E-Mail", "First Name", "Last Name"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		scrollPane_2.setViewportView(table);

		btn_delete = new JButton("Delete");
		btn_delete.setBackground(Color.WHITE);
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tf_id.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please select the student you want to delete!");
				}

				else {
					studentService.deleteStudent(Long.parseLong(tf_id.getText()));
					contactService.deleteContact(Long.parseLong(tf_id.getText()));
					makeGetAllTable();
					removeTextField();
				}

			}
		});
		btn_delete.setBounds(1024, 449, 124, 23);
		frmStudentInformationSystem.getContentPane().add(btn_delete);

		btn_update = new JButton("Update");
		btn_update.setBackground(Color.WHITE);
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tf_id.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please select the student you want to update!");
				} else {

					updateStudentwithId();
					makeGetAllTable();
					removeTextField();
				}

			}
		});
		btn_update.setBounds(1024, 398, 124, 23);
		frmStudentInformationSystem.getContentPane().add(btn_update);

		btn_save = new JButton("Save");
		btn_save.setBackground(Color.WHITE);
		btn_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tf_firstname.getText().isEmpty() || tf_lastname.getText().isEmpty() || tf_email.getText().isEmpty()
						|| !tf_email.getText().contains("@")) {
					JOptionPane.showMessageDialog(null, "Please fill in the mandatory fields! and need @ for email");
				} else {
					Contact contact = contactService.makeContact(tf_phone1.getText(), addres1.getText(),
							tf_phone2.getText(), addres2.getText());

					try {
						FileInputStream fis = new FileInputStream(imagePaths);
						byte[] data = new byte[fis.available()];
						fis.read(data);

						studentService.makeStudent(tf_firstname.getText(), tf_lastname.getText(), tf_email.getText(),
								String.valueOf(cb_gender.getSelectedItem()), contact, data);
						tf_phone1.setEditable(true);
						tf_phone2.setEditable(true);
						removeTextField();
						lbl_foto.setIcon(null);
					} catch (IOException e1) {
						e1.printStackTrace();
					}

				}

			}
		});

		btn_save.setBounds(1024, 364, 124, 23);
		frmStudentInformationSystem.getContentPane().add(btn_save);

		btn_getall = new JButton("Get All");
		btn_getall.setBackground(Color.WHITE);
		btn_getall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				makeGetAllTable();

			}
		});
		btn_getall.setBounds(1024, 328, 124, 23);
		frmStudentInformationSystem.getContentPane().add(btn_getall);

		lbl_language = new JLabel("Language");
		lbl_language.setForeground(Color.WHITE);
		lbl_language.setBackground(Color.DARK_GRAY);
		lbl_language.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_language.setBounds(1024, 483, 75, 14);
		frmStudentInformationSystem.getContentPane().add(lbl_language);

		comboBox_language = new JComboBox();
		comboBox_language.setForeground(Color.WHITE);
		comboBox_language.setBackground(Color.DARK_GRAY);
		comboBox_language.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (comboBox_language.getSelectedIndex() == 1) {
					Locale.setDefault(new Locale("fr", "FR"));
					i18n();
				} else if (comboBox_language.getSelectedIndex() == 0) {
					Locale.setDefault(new Locale("en", "EN"));
					i18n();
				} else if (comboBox_language.getSelectedIndex() == 2) {
					Locale.setDefault(new Locale("tr", "TR"));
					i18n();
				}

			}
		});
		comboBox_language.setModel(new DefaultComboBoxModel(new String[] { "English", "French", "Turkish" }));
		comboBox_language.setBounds(1024, 508, 124, 22);
		frmStudentInformationSystem.getContentPane().add(comboBox_language);

		lbl_foto2 = new JLabel("");
		lbl_foto2.setEnabled(false);
		lbl_foto2.setBounds(698, 297, 209, 16);
		frmStudentInformationSystem.getContentPane().add(lbl_foto2);

		btn_fotoekle = new JButton("Add Photo");
		btn_fotoekle.setBackground(Color.WHITE);
		btn_fotoekle.setBounds(1027, 283, 112, 23);
		frmStudentInformationSystem.getContentPane().add(btn_fotoekle);
		btn_fotoekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new File("user.dir"));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("All Pic", "png", "jpg", "jpeg", "gif");
				fc.addChoosableFileFilter(filter);

				int a = fc.showSaveDialog(null);

				if (a == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					String p = f.getAbsolutePath();

					imagePaths = fc.getSelectedFile().getAbsolutePath();
					lbl_foto.setIcon(seticon(p, image));

				}

			}
		});
	}

	public void searchStudentwithLastName(String lastName) {

		List<Student> lastNameList = studentService.withLastName(lastName);
		makeTable(lastNameList);
		removeTextField();
	}

	public void makeGetAllTable() {
		List<Student> studentList = studentService.getAll();
		makeTable(studentList);
	}

	public void searchStudentwithFirstName(String firstName) {

		List<Student> firstNameList = studentService.withFirstName(firstName);
		makeTable(firstNameList);
		removeTextField();

	}

	public void findbyEmail(String email) {
		List<Student> student = studentService.findByEmail(email);
		makeTable(student);
		removeTextField();

	}

	public void makeTable(List<Student> studentList) {

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Object[] column = new Object[4];
		model.setRowCount(0);
		for (int i = 0; i < studentList.size(); i++) {

			column[0] = studentList.get(i).getId();
			column[1] = studentList.get(i).getEmail();
			column[2] = studentList.get(i).getFirstName();
			column[3] = studentList.get(i).getLastName();
			model.addRow(column);

		}
	

	}
	
	


	public void studentInformation(long id) {

		Student student = studentService.findStudentInfo(id);
		tf_firstname.setText(student.getFirstName());
		tf_lastname.setText(student.getLastName());

		tf_email.setText(student.getEmail());

		tf_id.setText(String.valueOf(id));

		tf_phone1.setText(student.getContact().getPhone());
		tf_phone2.setText(student.getContact().getPhone1());
		addres1.setText(student.getContact().getAddres());
		addres2.setText(student.getContact().getAddress1());

		byte[] imageData = student.getImage();
		ImageIcon format = new ImageIcon(imageData);
		Image mm = format.getImage();
		Image img2 = mm.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(img2);

		lbl_foto.setIcon(image);

		if (student.getGender().equals("MAN")) {
			cb_gender.setSelectedIndex(0);
		} else if (student.getGender().equals("WOMAN")) {
			cb_gender.setSelectedIndex(1);
		}

	}

	public void removeTextField() {
		tf_firstname.setText("");
		tf_lastname.setText("");
		tf_email.setText("");
		tf_phone1.setText("");
		tf_phone2.setText("");
		addres1.setText("");
		addres2.setText("");
		tf_id.setText("");
		lbl_foto.setIcon(null);
		tf_phone1.setEditable(true);
		tf_phone2.setEditable(true);
	}

	public void tableMaking(Student student) {

		List<Student> studentList = new ArrayList();
		studentList.add(student);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Object[] column = new Object[4];
		model.setRowCount(0);
		for (int i = 0; i < studentList.size(); i++) {
			column[0] = studentList.get(i).getId();
			column[1] = studentList.get(i).getEmail();
			column[2] = studentList.get(i).getFirstName();
			column[3] = studentList.get(i).getLastName();
			model.addRow(column);
		}
		
	}

	
	public void updateStudentwithId() {
		Contact contact = contactService.updateContact(Long.parseLong(tf_id.getText()), tf_phone1.getText(),
				addres1.getText(), tf_phone2.getText(), addres2.getText());

		studentService.updateStudent(Long.parseLong(tf_id.getText()), tf_firstname.getText(), tf_lastname.getText(),
				tf_email.getText(), String.valueOf(cb_gender.getSelectedItem()), contact);

	}

}
