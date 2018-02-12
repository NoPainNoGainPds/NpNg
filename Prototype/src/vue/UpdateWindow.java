package vue;

import model.Boutique;
import model.Produit;
import utils.Constants;
import utils.DAO;
import utils.ModelObject;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class qui represente une fenetre qui permet de modifier tout les champs d'un objet metier et de l'enregistrer en base de données
 * @param <T>
 */
public class UpdateWindow<T> extends JFrame {
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
                    System.out.println("REF:"+name);
                    Class[] type = methodes[j].getParameterTypes();
                    try
                    {
                        //c'est compliqué mais en gros ici je recupere la liste des id en reference.
                        ArrayList list = ((DAO)(((ModelObject)type[0].newInstance()).getDaoClass().newInstance())).findFromReference();
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

                    }catch(InstantiationException e)
                    {
                        e.printStackTrace();
                    }catch(IllegalAccessException e)
                    {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

                }
                else
                {
                    if(methodes[j].getParameterTypes()[0] == String.class) {
                        //System.out.println(name);
                        JTextField textField = new JTextField(25);
                        textField.addActionListener(event -> {
                                    String s = textField.getText();
                                    try {
                                        methodes[j].invoke(update, (Object) s);
                                    } catch (IllegalAccessException e1) {
                                        e1.printStackTrace();
                                    } catch (InvocationTargetException e1) {
                                        e1.printStackTrace();
                                    }
                                    System.out.println(update);
                                }
                        );
                        TextLabel temp = new TextLabel(textField, new JLabel(name));
                        this.fields.add(temp);
                    }
                    else if(methodes[j].getParameterTypes()[0] == int.class)
                    {
                        JTextField textField = new JTextField(25);
                        textField.addActionListener(event -> {
                                    String s = textField.getText();
                                    if(s.matches("*"))
                                    {
                                        try {
                                            methodes[j].invoke(update, (Object) s);
                                        } catch (IllegalAccessException e1) {
                                            e1.printStackTrace();
                                        } catch (InvocationTargetException e1) {
                                            e1.printStackTrace();
                                        }
                                        System.out.println(update);
                                    }
                                    else
                                    {
                                        System.out.println("erreur");
                                    }
                                }
                        );
                        TextLabel temp = new TextLabel(textField, new JLabel(name));
                        this.fields.add(temp);
                    }
                    else if(methodes[j].getParameterTypes()[0] == Date.class)
                    {
                        JDateChooser jdc = new JDateChooser();
                        TextLabel temp = new TextLabel(jdc, new JLabel(name));
                        this.fields.add(temp);
                        System.out.println("DATE");
                    }
                }
            }
        }
        //ajout a la fenetre

        for(TextLabel textLabel : fields)
        {
            panel.add(textLabel.label);
            panel.add(textLabel.field);
        }
        this.add(panel);
        JButton bouton = new JButton("Save");
        bouton.addActionListener(event ->
        {
            try {
                if(!((DAO)(((ModelObject)update).getDaoClass().newInstance())).update(update))
                {
                    System.out.println("Erreur lors de la sauvegarde");
                }
                else
                {
                    System.out.println("GOOD!!!");
                }
                System.out.println("Saved");
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        panel.add(bouton);
        this.setSize(Constants.WIDTH,Constants.HEIGHT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}
class TextLabel<J extends JComponent>
{
    public J field;
    public JLabel label;

    public TextLabel(J field, JLabel label) {
        this.field = field;
        this.label = label;
    }
}
