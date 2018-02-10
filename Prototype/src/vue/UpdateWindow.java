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

public class UpdateWindow<T> extends JFrame {
    private T update;
    private ArrayList<JTextField> fields;
    public UpdateWindow(T update)
    {
        this.update = update;
        Method[] methodes = update.getClass().getMethods();
        this.fields = new ArrayList<>();
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
                    Class type2 = type[0];
                    try
                    {
                        //c'est compliqué mais en gros ici je recupere la liste des id en reference.
                        ArrayList list = ((DAO)((ModelObject)type[0].newInstance()).getDaoClass().newInstance()).findFromReference(0);
                    }catch(InstantiationException e)
                    {
                        e.printStackTrace();
                    }catch(IllegalAccessException e)
                    {
                        e.printStackTrace();
                    }

                }
                else
                {
                    System.out.println(name);
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
        JPanel panel = new JPanel();
        for(JTextField textField : fields)
        {
            panel.add(textField);
        }
        this.add(panel);
        this.setSize(Constants.WIDTH,Constants.HEIGHT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}
