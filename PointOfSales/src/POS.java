import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.ImageIcon;

public class POS {

	private JFrame frame;
	private JTable table;
	private JTextField textField;
	private JTextField textTax;
	private JTextField textSub;
	private JTextField textTotal;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					POS window = new POS();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public POS() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 */
	
	
	private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        if(model.getRowCount()==0){
            //if table is empty
        	JOptionPane.showMessageDialog(frame, this, "Table Empty", 0);
        }
        else{
            String Item, Qty, Amount;
            try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost/sales", "postgres","100ee20gg");
            
            for(int j=0; j<model.getRowCount(); j++){
                Item = model.getValueAt(j, 0).toString();
                Qty = model.getValueAt(j, 1).toString();
                Amount  = model.getValueAt(j, 2).toString();
                
                
                PreparedStatement pst = con.prepareStatement("insert into items(items,qty,amount)values(?,?,?)");
                pst.setString(1, Item);
                pst.setString(2, Qty);
                pst.setString(3, Amount);
                pst.executeUpdate();
                
            }
            JOptionPane.showMessageDialog(null,"Record Added!");
            //clear table
            model.setRowCount(0);
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        }
        }
    
	
	public void ItemCost()
	{
		double sum = 0;
		double tax =3.9;
		
		for (int i = 0; i < table.getRowCount(); i++)
		{
			sum = sum + Double.parseDouble(table.getValueAt(i, 2).toString());
		}
		textSub.setText(Double.toString(sum));
		double cTotal = Double.parseDouble(textSub.getText());
		
		double cTax = (cTotal * tax)/100;
		String iTaxTotal = String.format("Ksh %.2f", cTax);
		textTax.setText(iTaxTotal);
		
		String iSubTotal = String.format("Ksh %.2f", cTotal);
		textSub.setText(iSubTotal);
		
		String iTotal = String.format("Ksh %.2f", cTotal + cTax);
		textTotal.setText(iTotal);
		
		String Barcode = String.format("Total is  %.2f", cTotal + cTax);
		textField.setText(Barcode);
	}
	
	public void Change()
	{
		double sum = 0;
		double tax =3.9;
		double cash =Double.parseDouble(textField_1.getText());
		
		for (int i = 0; i < table.getRowCount(); i++)
		{
			sum = sum + Double.parseDouble(table.getValueAt(i, 2).toString());
		}
		
		
		double cTax = (tax * sum)/100;
		double cChange = (cash - (sum + cTax));
		String ChangeGiven = String.format("Ksh %.2f", cChange);
		textField_2.setText(ChangeGiven);
		
		
	}
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1450, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(21, 11, 346, 410);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btn7 = new JButton("7");
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String Enternumber = textField_1.getText();
				
				if (Enternumber == "") 
				{
					textField_1.setText(btn7.getText());
				}
				else
				{
					Enternumber = textField_1.getText() + btn7.getText();
					textField_1.setText(Enternumber);
					}
				
			}
		});
		btn7.setFont(new Font("Tahoma", Font.BOLD, 44));
		btn7.setBounds(64, 25, 72, 56);
		panel.add(btn7);
		
		JButton btn8 = new JButton("8");
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String Enternumber = textField_1.getText();
				
				if (Enternumber == "") 
				{
					textField_1.setText(btn8.getText());
				}
				else
				{
					Enternumber = textField_1.getText() + btn8.getText();
					textField_1.setText(Enternumber);
					}
			}
		});
		btn8.setFont(new Font("Tahoma", Font.BOLD, 44));
		btn8.setBounds(155, 25, 72, 56);
		panel.add(btn8);
		
		JButton btn9 = new JButton("9");
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String Enternumber = textField_1.getText();
				
				if (Enternumber == "") 
				{
					textField_1.setText(btn9.getText());
				}
				else
				{
					Enternumber = textField_1.getText() + btn9.getText();
					textField_1.setText(Enternumber);
					}
			}
		});
		btn9.setFont(new Font("Tahoma", Font.BOLD, 44));
		btn9.setBounds(250, 25, 72, 56);
		panel.add(btn9);
		
		JButton btn4 = new JButton("4");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String Enternumber = textField_1.getText();
				
				if (Enternumber == "") 
				{
					textField_1.setText(btn4.getText());
				}
				else
				{
					Enternumber = textField_1.getText() + btn4.getText();
					textField_1.setText(Enternumber);
					}
			}
		});
		btn4.setFont(new Font("Tahoma", Font.BOLD, 44));
		btn4.setBounds(64, 104, 72, 56);
		panel.add(btn4);
		
		JButton btn1 = new JButton("1");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String Enternumber = textField_1.getText();
				
				if (Enternumber == "") 
				{
					textField_1.setText(btn1.getText());
				}
				else
				{
					Enternumber = textField_1.getText() + btn1.getText();
					textField_1.setText(Enternumber);
					}
			}
		});
		btn1.setFont(new Font("Tahoma", Font.BOLD, 44));
		btn1.setBounds(64, 188, 72, 56);
		panel.add(btn1);
		
		JButton btn0 = new JButton("0");
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String Enternumber = textField_1.getText();
				
				if (Enternumber == "") 
				{
					textField_1.setText(btn0.getText());
				}
				else
				{
					Enternumber = textField_1.getText() + btn0.getText();
					textField_1.setText(Enternumber);
					}
			}
		});
		btn0.setFont(new Font("Tahoma", Font.BOLD, 44));
		btn0.setBounds(64, 273, 72, 56);
		panel.add(btn0);
		
		JButton btn5 = new JButton("5");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String Enternumber = textField_1.getText();
				
				if (Enternumber == "") 
				{
					textField_1.setText(btn5.getText());
				}
				else
				{
					Enternumber = textField_1.getText() + btn5.getText();
					textField_1.setText(Enternumber);
					}
			}
		});
		btn5.setFont(new Font("Tahoma", Font.BOLD, 44));
		btn5.setBounds(155, 104, 72, 56);
		panel.add(btn5);
		
		JButton btn2 = new JButton("2");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String Enternumber = textField_1.getText();
				
				if (Enternumber == "") 
				{
					textField_1.setText(btn2.getText());
				}
				else
				{
					Enternumber = textField_1.getText() + btn2.getText();
					textField_1.setText(Enternumber);
					}
			}
		});
		btn2.setFont(new Font("Tahoma", Font.BOLD, 44));
		btn2.setBounds(155, 188, 72, 56);
		panel.add(btn2);
		
		JButton btnDot = new JButton(".");
		btnDot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (! textField_1.getText().contains("."))
				{
					textField_1.setText(textField_1.getText() + btnDot.getText());
				}
				}
		});
		btnDot.setFont(new Font("Tahoma", Font.BOLD, 44));
		btnDot.setBounds(155, 273, 72, 56);
		panel.add(btnDot);
		
		JButton btn6 = new JButton("6");
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String Enternumber = textField_1.getText();
				
				if (Enternumber == "") 
				{
					textField_1.setText(btn6.getText());
				}
				else
				{
					Enternumber = textField_1.getText() + btn6.getText();
					textField_1.setText(Enternumber);
					}
			}
		});
		btn6.setFont(new Font("Tahoma", Font.BOLD, 44));
		btn6.setBounds(250, 104, 72, 56);
		panel.add(btn6);
		
		JButton btn3 = new JButton("3");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String Enternumber = textField_1.getText();
				
				if (Enternumber == "") 
				{
					textField_1.setText(btn3.getText());
				}
				else
				{
					Enternumber = textField_1.getText() + btn3.getText();
					textField_1.setText(Enternumber);
					}
			}
		});
		btn3.setFont(new Font("Tahoma", Font.BOLD, 44));
		btn3.setBounds(250, 188, 72, 56);
		panel.add(btn3);
		
		JButton btnC = new JButton("C");
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textField_1.setText(null);
				textField_2.setText(null);
			}
		});
		btnC.setFont(new Font("Tahoma", Font.BOLD, 44));
		btnC.setBounds(250, 273, 72, 56);
		panel.add(btnC);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(744, 11, 680, 420);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnA = new JButton("");
		btnA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				double PriceOfItem = 200;
				DefaultTableModel model =(DefaultTableModel) table.getModel();
				model.addRow(new Object[] {"Screen1","1",PriceOfItem});
				ItemCost();
			}
		});
		btnA.setIcon(new ImageIcon("C:\\Users\\Sharon\\Pictures\\Screenshots\\Screenshot (1).png"));
		btnA.setFont(new Font("Tahoma", Font.BOLD, 44));
		btnA.setBounds(10, 11, 113, 126);
		panel_1.add(btnA);
		
		JButton btnF = new JButton("7");
		btnF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 120;
				DefaultTableModel model =(DefaultTableModel) table.getModel();
				model.addRow(new Object[] {"Screen6","1",PriceOfItem});
				ItemCost();
			}
		});
		btnF.setIcon(new ImageIcon("C:\\Users\\Sharon\\Pictures\\Screenshots\\Screenshot (6).png"));
		btnF.setFont(new Font("Tahoma", Font.BOLD, 44));
		btnF.setBounds(10, 148, 113, 126);
		panel_1.add(btnF);
		
		JButton btnK = new JButton("7");
		btnK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 240;
				DefaultTableModel model =(DefaultTableModel) table.getModel();
				model.addRow(new Object[] {"Screen11","1",PriceOfItem});
				ItemCost();
			}
		});
		btnK.setIcon(new ImageIcon("C:\\Users\\Sharon\\Pictures\\Screenshots\\Screenshot (11).png"));
		btnK.setFont(new Font("Tahoma", Font.BOLD, 44));
		btnK.setBounds(10, 285, 113, 126);
		panel_1.add(btnK);
		
		JButton btnB = new JButton("7");
		btnB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				double PriceOfItem = 100;
				DefaultTableModel model =(DefaultTableModel) table.getModel();
				model.addRow(new Object[] {"Screen2","1",PriceOfItem});
				ItemCost();
			}
		});
		btnB.setIcon(new ImageIcon("C:\\Users\\Sharon\\Pictures\\Screenshots\\Screenshot (2).png"));
		btnB.setFont(new Font("Tahoma", Font.BOLD, 44));
		btnB.setBounds(133, 11, 113, 126);
		panel_1.add(btnB);
		
		JButton btnCc = new JButton("7");
		btnCc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				double PriceOfItem = 80;
				DefaultTableModel model =(DefaultTableModel) table.getModel();
				model.addRow(new Object[] {"Screen3","1",PriceOfItem});
				ItemCost();
			}
		});
		btnCc.setIcon(new ImageIcon("C:\\Users\\Sharon\\Pictures\\Screenshots\\Screenshot (3).png"));
		btnCc.setFont(new Font("Tahoma", Font.BOLD, 44));
		btnCc.setBounds(256, 11, 113, 126);
		panel_1.add(btnCc);
		
		JButton btnD = new JButton("7");
		btnD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				double PriceOfItem = 50;
				DefaultTableModel model =(DefaultTableModel) table.getModel();
				model.addRow(new Object[] {"Screen4","1",PriceOfItem});
				ItemCost();
			}
		});
		btnD.setIcon(new ImageIcon("C:\\Users\\Sharon\\Pictures\\Screenshots\\Screenshot (4).png"));
		btnD.setFont(new Font("Tahoma", Font.BOLD, 44));
		btnD.setBounds(379, 11, 113, 126);
		panel_1.add(btnD);
		
		JButton btnG = new JButton("7");
		btnG.setIcon(new ImageIcon("C:\\Users\\Sharon\\Pictures\\Screenshots\\Screenshot (7).png"));
		btnG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 500;
				DefaultTableModel model =(DefaultTableModel) table.getModel();
				model.addRow(new Object[] {"Screen7","1",PriceOfItem});
				ItemCost();
			}
		});
		btnG.setFont(new Font("Tahoma", Font.BOLD, 44));
		btnG.setBounds(133, 148, 113, 126);
		panel_1.add(btnG);
		
		JButton btnH = new JButton("7");
		btnH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 350;
				DefaultTableModel model =(DefaultTableModel) table.getModel();
				model.addRow(new Object[] {"Screen8","1",PriceOfItem});
				ItemCost();
			}
		});
		btnH.setIcon(new ImageIcon("C:\\Users\\Sharon\\Pictures\\Screenshots\\Screenshot (8).png"));
		btnH.setFont(new Font("Tahoma", Font.BOLD, 44));
		btnH.setBounds(256, 148, 113, 126);
		panel_1.add(btnH);
		
		JButton btnI = new JButton("7");
		btnI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 420;
				DefaultTableModel model =(DefaultTableModel) table.getModel();
				model.addRow(new Object[] {"Screen9","1",PriceOfItem});
				ItemCost();
			}
		});
		btnI.setIcon(new ImageIcon("C:\\Users\\Sharon\\Pictures\\Screenshots\\Screenshot (9).png"));
		btnI.setFont(new Font("Tahoma", Font.BOLD, 44));
		btnI.setBounds(379, 148, 113, 126);
		panel_1.add(btnI);
		
		JButton btnL = new JButton("7");
		btnL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 260;
				DefaultTableModel model =(DefaultTableModel) table.getModel();
				model.addRow(new Object[] {"Screen13","1",PriceOfItem});
				ItemCost();
			}
		});
		btnL.setIcon(new ImageIcon("C:\\Users\\Sharon\\Pictures\\Screenshots\\Screenshot (12).png"));
		btnL.setFont(new Font("Tahoma", Font.BOLD, 44));
		btnL.setBounds(133, 285, 113, 126);
		panel_1.add(btnL);
		
		JButton btnM = new JButton("7");
		btnM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnM.setIcon(new ImageIcon("C:\\Users\\Sharon\\Pictures\\Screenshots\\Screenshot (8).png"));
		btnM.setFont(new Font("Tahoma", Font.BOLD, 44));
		btnM.setBounds(256, 285, 113, 126);
		panel_1.add(btnM);
		
		JButton btnN = new JButton("7");
		btnN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 140;
				DefaultTableModel model =(DefaultTableModel) table.getModel();
				model.addRow(new Object[] {"Screen14","1",PriceOfItem});
				ItemCost();
			}
		});
		btnN.setIcon(new ImageIcon("C:\\Users\\Sharon\\Pictures\\Screenshots\\Screenshot (5).png"));
		btnN.setFont(new Font("Tahoma", Font.BOLD, 44));
		btnN.setBounds(379, 285, 113, 126);
		panel_1.add(btnN);
		
		JButton btnE = new JButton("7");
		btnE.setIcon(new ImageIcon("C:\\Users\\Sharon\\Pictures\\Screenshots\\Screenshot (5).png"));
		btnE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				double PriceOfItem = 180;
				DefaultTableModel model =(DefaultTableModel) table.getModel();
				model.addRow(new Object[] {"Screen5","1",PriceOfItem});
				ItemCost();
			}
		});
		btnE.setFont(new Font("Tahoma", Font.BOLD, 44));
		btnE.setBounds(502, 11, 113, 126);
		panel_1.add(btnE);
		
		JButton btnJ = new JButton("7");
		btnJ.setIcon(new ImageIcon("C:\\Users\\Sharon\\Pictures\\Screenshots\\Screenshot (10).png"));
		btnJ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 250;
				DefaultTableModel model =(DefaultTableModel) table.getModel();
				model.addRow(new Object[] {"Screen10","1",PriceOfItem});
				ItemCost();
			}
		});
		btnJ.setFont(new Font("Tahoma", Font.BOLD, 44));
		btnJ.setBounds(502, 148, 113, 126);
		panel_1.add(btnJ);
		
		JButton btnO = new JButton("7");
		btnO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 225;
				DefaultTableModel model =(DefaultTableModel) table.getModel();
				model.addRow(new Object[] {"Screen15","1",PriceOfItem});
				ItemCost();
			}
		});
		btnO.setIcon(new ImageIcon("C:\\Users\\Sharon\\Pictures\\Screenshots\\Screenshot (12).png"));
		btnO.setFont(new Font("Tahoma", Font.BOLD, 44));
		btnO.setBounds(502, 285, 113, 126);
		panel_1.add(btnO);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBounds(-240, 432, 1403, 295);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_3.setBounds(42, 33, 391, 177);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblTax = new JLabel("Tax");
		lblTax.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTax.setBounds(24, 23, 72, 29);
		panel_3.add(lblTax);
		
		textTax = new JTextField();
		textTax.setBounds(201, 26, 157, 26);
		panel_3.add(textTax);
		textTax.setColumns(10);
		
		JLabel lblSubtotal = new JLabel("Subtotal");
		lblSubtotal.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblSubtotal.setBounds(24, 63, 101, 29);
		panel_3.add(lblSubtotal);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTotal.setBounds(24, 98, 72, 29);
		panel_3.add(lblTotal);
		
		textSub = new JTextField();
		textSub.setColumns(10);
		textSub.setBounds(201, 63, 157, 26);
		panel_3.add(textSub);
		
		textTotal = new JTextField();
		textTotal.setColumns(10);
		textTotal.setBounds(201, 98, 157, 26);
		panel_3.add(textTotal);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setLayout(null);
		panel_3_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_3_1.setBounds(477, 33, 391, 177);
		panel_2.add(panel_3_1);
		
		JLabel lblDisplayCash = new JLabel("Display Cash");
		lblDisplayCash.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblDisplayCash.setBounds(33, 70, 151, 29);
		panel_3_1.add(lblDisplayCash);
		
		JLabel lblChange = new JLabel("Change");
		lblChange.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblChange.setBounds(33, 110, 140, 29);
		panel_3_1.add(lblChange);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(213, 70, 157, 26);
		panel_3_1.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(213, 110, 157, 26);
		panel_3_1.add(textField_2);
		
		JLabel lblPayMethod = new JLabel("Pay Method");
		lblPayMethod.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblPayMethod.setBounds(33, 30, 166, 29);
		panel_3_1.add(lblPayMethod);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Cash", "Visa Card", "Master card"}));
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 24));
		comboBox.setBounds(213, 30, 157, 29);
		panel_3_1.add(comboBox);
		
		JPanel panel_3_2 = new JPanel();
		panel_3_2.setLayout(null);
		panel_3_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_3_2.setBounds(916, 33, 391, 229);
		panel_2.add(panel_3_2);
		
		JButton btnPay = new JButton("Pay");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (comboBox.getSelectedItem().equals("cash"))
				
				{
					Change();
				}
				else
				{
					textField_1.setText("");
					textField_2.setText("");
					
				}
			}
		});
		btnPay.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnPay.setBounds(26, 31, 82, 47);
		panel_3_2.add(btnPay);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MessageFormat header = new MessageFormat("Printing in progress");
				MessageFormat footer = new MessageFormat("Page {0, number, integer}");
				
				try
				{
					table.print(JTable.PrintMode.NORMAL,header,footer);
				}
				catch(java.awt.print.PrinterException ex)
				{
					System.err.format("No Printer found", ex.getMessage());
				}
				
			}
		});
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnPrint.setBounds(146, 31, 110, 47);
		panel_3_2.add(btnPrint);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.setText(null);
				textField_2.setText(null);
				textField.setText(null);
				textTotal.setText(null);
				textTax.setText(null);
				textSub.setText(null);
				
				DefaultTableModel RecordTable = (DefaultTableModel) table.getModel();
				RecordTable.setRowCount(0);

			}
		});
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnReset.setBounds(266, 31, 104, 47);
		panel_3_2.add(btnReset);
		
		JButton btnRemove = new JButton("Remove Item");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				int RemoveItem = table.getSelectedRow();
				if (RemoveItem >= 0)
				{
				model.removeRow(RemoveItem);
				}
				ItemCost();
		
				
				if (comboBox.getSelectedItem().equals("cash"))
					
				{
					Change();
				}
				else
				{
					textField_2.setText("");
					textField_1.setText("");
				}
			}
		});
		btnRemove.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnRemove.setBounds(26, 97, 230, 47);
		panel_3_2.add(btnRemove);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frame, "Confirm your exit","Point of Sale",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION)
				{
					System.exit(0);
				}
				
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnExit.setBounds(288, 97, 82, 47);
		panel_3_2.add(btnExit);
		
		JButton btnJson = new JButton("Generate Json");
		btnJson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    Json.main(new String[0]);
                } catch (Exception throwables) {
                    throwables.printStackTrace();
                }
				
				
			}
		});
		btnJson.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnJson.setBounds(170, 155, 211, 49);
		panel_3_2.add(btnJson);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnSaveActionPerformed(evt);
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnSave.setBounds(26, 157, 110, 47);
		panel_3_2.add(btnSave);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(377, 11, 356, 349);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setForeground(new Color(0, 0, 0));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Items", "Qty", "Amount"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(125);
		table.getColumnModel().getColumn(0).setMinWidth(125);
		table.getColumnModel().getColumn(1).setPreferredWidth(60);
		scrollPane.setViewportView(table);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 36));
		textField.setBounds(377, 371, 357, 61);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
}
