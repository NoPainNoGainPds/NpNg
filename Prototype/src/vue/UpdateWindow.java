package vue;

import model.Boutique;
import model.Produit;
import utils.Constants;
import utils.DAO;
import utils.ModelObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Class qui represente une fenetre qui permet de modifier tout les champs d'un objet metier et de l'enregistrer en base de données
 * @param <T>
 */
public class UpdateWindow<T> extends JFrame {
    private T update;
    private ArrayList<JTextField> fields;
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
                        //c'est compliqué mais en gros ici je recupere la liste des id en reference.
                        ArrayList list = ((DAO)(((ModelObject)type[0].newInstance()).getDaoClass().newInstance())).findFromReference(0);
                        String met = name.substring(3,name.length()-3);
                        met = "get"+met;
                        Method meth = update.getClass().getMethod(met,null);
                        System.out.println(met);
                        JComboBox listDeroulante = new JComboBox(list.toArray());
                        listDeroulante.setSelectedItem(meth.invoke(update));
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
                    this.fields.add(textField);
                }
            }
        }
        //ajout a la fenetre

        for(JTextField textField : fields)
        {
            panel.add(textField);
        }
        this.add(panel);
        JButton bouton = new JButton("Save");
        bouton.addActionListener(event ->
        {
            try {
                if(((DAO)(((ModelObject)update).getDaoClass().newInstance())).update(update))
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
