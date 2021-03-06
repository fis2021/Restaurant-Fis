package org.restaurantfis.sre.model;
import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class foodMenuPage extends JFrame implements ActionListener
{
    public JLabel page_title ;
    public Container menu_page ;

    public JTable menu ;

    public Object[] columns = {"FOOD","FOOD PRICE","DRINKS","DRINKS PRICE"};
    public Object[] row = new Object[4];
    public DefaultTableModel model;
    public JScrollPane pane ;

    public JTextField food,food_price,drinks,drinks_price;
    public JButton add_button, delete_button, save_button, edit_button;

    public ActionListener add_action, delete_action, save_action;


    public foodMenuPage()
    {
        setTitle("Restaurant Food Menu");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        menu_page = getContentPane();
        menu_page.setBackground(Color.gray);
        menu_page.setLayout(null);

        //titlul
        page_title = new JLabel("Restaurant Food Menu");
        page_title.setFont(new Font("Arial", Font.BOLD, 40));
        page_title.setSize(500, 50);
        page_title.setLocation(250, 30);
        menu_page.add(page_title);
        //tabel
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        menu = new JTable(model);
        ImportdataFromfiles();

        pane = new JScrollPane(menu);
        pane.setBounds(175, 200,550,200);
        menu_page.add(pane);

        //edit button
        edit_button = new JButton("EDIT");
        edit_button.setFont(new Font("Arial", Font.PLAIN,15));
        edit_button.setForeground(Color.black);
        edit_button.setLocation(50,30);
        edit_button.setSize(75,50);
        edit_button.setBackground(Color.gray);
        edit_button.addActionListener(this);
        menu_page.add(edit_button);




        //edit text fields
        food = new JTextField();
        food_price = new JTextField();
        drinks = new JTextField() ;
        drinks_price = new JTextField();

        food.setBounds(50,235,100,35);
        food_price.setBounds(50,270,100,35);
        drinks.setBounds(50,305,100,35);
        drinks_price.setBounds(50,340,100,35);

        food.setVisible(false);
        food_price.setVisible(false);
        drinks_price.setVisible(false);
        drinks.setVisible(false);

        menu_page.add(food_price);
        menu_page.add(food);
        menu_page.add(drinks);
        menu_page.add(drinks_price);

        //edit buttons
        add_button = new JButton("ADD");
        add_button.addActionListener(this);
        delete_button = new JButton("DELETE");
        delete_button.addActionListener(this);
        save_button = new JButton("SAVE");
        save_button.addActionListener(this);

        add_button.setBounds(50,100,60,30);
        delete_button.setBounds(50,135,80,30);
        save_button.setBounds(50,170,60,30);
        add_button.setVisible(false);
        delete_button.setVisible(false);
        save_button.setVisible(false);

        menu_page.add(add_button);
        menu_page.add(delete_button);
        menu_page.add(save_button);
        

        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("EDIT")) {
            food.setVisible(true);
            food_price.setVisible(true);
            drinks_price.setVisible(true);
            drinks.setVisible(true);

            add_button.setVisible(true);
            delete_button.setVisible(true);
            save_button.setVisible(true);
        } else if (e.getActionCommand().equals("ADD")) {
            row[0] = food.getText();
            row[1] = food_price.getText();
            row[2] = drinks.getText();
            row[3] = drinks_price.getText();
            model.addRow(row);
        } else if (e.getActionCommand().equals("DELETE")) {
            int i = menu.getSelectedRow();
            if (i >= 0) {
                model.removeRow(i);
            }
        } else {
            String filePath = "src/main/resources/menuModificationstorage.txt";
            File file = new File(filePath);

            try {
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);


                for (int i = 0; i < menu.getRowCount(); i++) {//rows
                    for (int j = 0; j < menu.getColumnCount(); j++) {//columns
                        bw.write(menu.getValueAt(i, j).toString() + " ");
                    }
                    bw.newLine();
                }

                bw.close();
                fw.close();

                bw.close();
                fw.close();
            } catch (IOException ex) {
                System.out.println("data not exported");
            }
        }
    }

        public void ImportdataFromfiles()
        {
           String filePath = "src/main/resources/menuModificationstorage.txt";
           File file = new File(filePath);
            try {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);

                DefaultTableModel model = (DefaultTableModel)menu.getModel();
                Object[] lines = br.lines().toArray();

                for(int i = 0; i < lines.length; i++){
                    String[] row = lines[i].toString().split(" ");
                    model.addRow(row);
                }

            } catch (FileNotFoundException ex)
            {
                System.out.println("data not imported");
            }
            }



    }

