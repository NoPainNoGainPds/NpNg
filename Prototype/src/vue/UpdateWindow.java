package vue;

import model.Boutique;
import model.Produit;
import utils.Constants;
import utils.DAO;
import utils.ModelObject;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class (who represente a window) which allows to change all fields of a business object and save that in the DB
 * @param <T>
 */
public class UpdateWindow<T extends ModelObject> extends JFrame {
    private T update;
    private ArrayList<TextLabel> fields;
    public UpdateWindow(T update)
    {
        this.update = update;
        Method[] methodes = update.getClass().getMethods();
        this.fields = new ArrayList<>();
        JPanel panel = new JPanel();
        for(int i= 0 ; i< methodes.length;i++)
        {
            String name = methodes[i].getName();

            int j = i;
            if(name.startsWith("set"))
            {
                if(name.endsWith("Ref"))
                {
                    //System.out.println("REF:"+name);
                    Class[] type = methodes[j].getParameterTypes();
                    try
                    {
                        //Recover the id list in reference.
                        //Class model = update.getDaoClass();
                        ModelObject model = (ModelObject)type[0].newInstance();
                        DAO dao = model.getDaoClass();
                        ArrayList<T> list = dao.findFromReference();
                        String met = name.substring(3,name.length()-3);
                        JLabel label = new JLabel(met);
                        met = "get"+met;

                        Method meth = update.getClass().getMethod(met);
                        //System.out.println(met);
                        JComboBox listDeroulante = new JComboBox(list.toArray());
                        //System.out.println(meth.invoke(update));
                        listDeroulante.getModel().setSelectedItem(meth.invoke(update));
                        //listDeroulante.setSelectedItem();
                        listDeroulante.addActionListener(event -> {
                            try {
                                System.out.println(listDeroulante.getSelectedItem());
                                methodes[j].invoke(update, listDeroulante.getSelectedItem());

                            } catch (IllegalAccessException e1) {
                                e1.printStackTrace();
                            } catch (InvocationTargetException e1) {
                                e1.printStackTrace();
                            }
                            System.out.println(update);
                        });
                        panel.add(label);
                        panel.add(listDeroulante);

                    }catch(IllegalAccessException e)
                    {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }

                }
                else
                {
                    if(methodes[j].getParameterTypes()[0] == String.class) {
                        //System.out.println(name);
                        JTextField textField = new JTextField(25);
                        textField.addActionListener(event -> {
                                    Object s = textField.getText();
                                    try {
                                        methodes[j].invoke(update, s);
                                    } catch (IllegalAccessException e1) {
                                        e1.printStackTrace();
                                    } catch (InvocationTargetException e1) {
                                        e1.printStackTrace();
                                    }
                                    System.out.println(update);
                                }
                        );
                        TextLabel<JTextField> temp = new TextLabel(textField, new JLabel(name));
                        this.fields.add(temp);
                    }
                    else if(methodes[j].getParameterTypes()[0] == int.class)
                    {
                        JTextField textField = new JTextField(25);
                        textField.addActionListener(event -> {
                                    String s = textField.getText();
                                    try {
                                        Object x = Integer.parseInt(s);
                                        if (s.matches("[0-9]+")) {
                                            try {
                                                methodes[j].invoke(update,x);
                                            } catch (IllegalAccessException e1) {
                                                e1.printStackTrace();
                                            } catch (InvocationTargetException e1) {
                                                e1.printStackTrace();
                                            }
                                            System.out.println(update);
                                        } else {
                                            System.out.println("erreur");
                                        }
                                    }catch(NumberFormatException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                        );
                        TextLabel temp = new TextLabel(textField, new JLabel(name));
                        this.fields.add(temp);
                    }
                    else if(methodes[j].getParameterTypes()[0] == Date.class)
                    {
                        JDateChooser jdc = new JDateChooser();
                        TextLabel<JDateChooser> temp = new TextLabel(jdc, new JLabel(name));
                        this.fields.add(temp);
                        System.out.println("DATE");
                    }
                }
            }
        }
        //Add the window

        for(TextLabel textLabel : fields)
        {
            panel.add(textLabel);
        }
        panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
        this.add(panel);
        JButton bouton = new JButton("Save");
        bouton.addActionListener(event ->
        {
            if(!update.getDaoClass().update(update))
            {
                System.out.println("Erreur lors de la sauvegarde");
            }
            else
            {
                System.out.println("GOOD!!!");
            }
            System.out.println("Saved");
        });
        panel.add(bouton);
        this.setSize(Constants.WIDTH,Constants.HEIGHT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}


