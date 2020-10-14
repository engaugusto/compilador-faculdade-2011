/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

/**
 *
 * @author Eduardo Froes
 */
public final class AutoCompletar {

        private JTextArea jTextArea1;
        private ArrayList<JMenuItem> Palavras;
        private JPopupMenu menu;
        private String Texto;
        private int CursorX = 0;
        private int Count = 0;

        public AutoCompletar(JTextArea Codigo, int CursorX)
        {
            jTextArea1=Codigo;
            menu = CarregaAutoCompletar();
            Texto = "";
            
        }

        public void AutoCompletar()
        {

            JMenuItem Aux1;
        //getMenu().setAutoscrolls(true);
            //getMenu().add(new ScrollPane());

                for(final Object Item : getMenu().getComponents())
                {
                    Aux1 = (JMenuItem)Item;
                    Aux1.setVisible(true);
                }

        getjTextArea1().addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {
               if(e.getKeyCode() == KeyEvent.VK_ALT)
                {
                    getMenu().show(e.getComponent(), jTextArea1.getLineCount(),jTextArea1.getColumns());
                }

            }

            public void keyPressed(KeyEvent e) {
                return;
            }

            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ALT)
                {
                     getMenu().show(e.getComponent(), jTextArea1.getLineCount(),jTextArea1.getColumns());
                }

            }

            //public void keySugestao(KeyEvent e){

            //}


        });



    }

    public JPopupMenu CarregaAutoCompletar()
    {
        setPalavras(new ArrayList<JMenuItem>());
        final JPopupMenu menu = new JPopupMenu();


        getPalavras().add(new JMenuItem("Scribe"));
        getPalavras().add(new JMenuItem("Verum"));
        getPalavras().add(new JMenuItem("Dum"));
        getPalavras().add(new JMenuItem("Lectio"));
        getPalavras().add(new JMenuItem("Iteratio"));
        getPalavras().add(new JMenuItem("Toto"));
        getPalavras().add(new JMenuItem("Et"));
        getPalavras().add(new JMenuItem("Vel"));
        getPalavras().add(new JMenuItem("Si"));
        getPalavras().add(new JMenuItem("Ni"));


        for(final JMenuItem Item : getPalavras())
        {
            Item.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if(Texto == "")
                    getjTextArea1().setText(getjTextArea1().getText()+Item.getText());
                else
                {
                    
                        String s = getjTextArea1().getText().substring(0, getjTextArea1().getCaretPosition() - Texto.length());
                        String c = getjTextArea1().getText().substring(getjTextArea1().getCaretPosition(), getjTextArea1().getText().length());

                        String item = Item.getText();
                        int Ponto = s.length() + item.length();
                        Font font = new Font("Arial",Font.BOLD,13);



                        getjTextArea1().setText(s);
                        getjTextArea1().setFont(font);
                        getjTextArea1().setText(getjTextArea1().getText() + item);
                        //getjTextArea1().setForeground(Color.orange);
                        getjTextArea1().setText(getjTextArea1().getText() + c);
                        //getjTextArea1().setFo;
                        //getjTextArea1().setText(s);
                        getjTextArea1().setCaretPosition(Ponto);
                 
                }
            }
        });

        menu.add(Item);
        }

        return menu;
    }

    public void setVisibleItem(final java.awt.event.KeyEvent evt)
    {
         JMenuItem Aux1;
         Character Text;
         Text=evt.getKeyChar();

         getMenu().setVisible(false);
         VerificaBotoes(evt);

        if(Texto.equals(""))
             Text = Text.toUpperCase(evt.getKeyChar());


        if( (evt.getKeyCode() != KeyEvent.VK_SPACE) &&(evt.getKeyCode() != KeyEvent.VK_BACK_SPACE)
                &&(evt.getKeyCode() != KeyEvent.VK_ENTER) )
        {
            
            setTexto(getTexto() + Text.toString());
            for(final Object Item : getMenu().getComponents())
            {
                Aux1 = (JMenuItem)Item;

                if(!Aux1.getText().contains(Texto))
                {
                    Aux1.setVisible(false);

                }
            }

            if((Texto != "") && !Vazio())
            {
                getMenu().setVisible(true);
                getMenu().show(evt.getComponent(),CursorX * 5 ,jTextArea1.getLineCount() * 16);
            }
            else
            {
                getMenu().setVisible(false);
            }
        }

        else
        {

             
             if((evt.getKeyCode() == KeyEvent.VK_BACK_SPACE))
             {
                 
                for(final Object Item : getMenu().getComponents())
                {
                    Aux1 = (JMenuItem)Item;
                    Aux1.setVisible(true);
                }
                
                if(Texto.length() >1)
                {

                    if(Texto.length() == 2)
                    {
                        setTexto(getTexto().substring(0, 1));
                        evt.setKeyChar(getTexto().charAt(0));
                        evt.setKeyCode((int)(getTexto().charAt(getTexto().length() - 1)));
                        setTexto("");
                    }

                    else
                    {
                        setTexto(getTexto().substring(0, getTexto().length() - 1));
                        evt.setKeyChar(getTexto().charAt(getTexto().length() - 1));
                        evt.setKeyCode((int)(getTexto().charAt(getTexto().length() - 1)));
                        setTexto(getTexto().substring(0, getTexto().length() - 1));
                    }

                    
                    this.setVisibleItem(evt);


                 }

                else
                {
                        if(Texto.length() == 1)
                        {
                            setTexto("");
                            for(final Object Item : getMenu().getComponents())
                            {
                                Aux1 = (JMenuItem)Item;
                                Aux1.setVisible(true);
                            }
                        }

                        else
                            Texto = "";
                }
             }

             else
             {
                if((evt.getKeyCode() == KeyEvent.VK_SPACE) || (evt.getKeyCode() == KeyEvent.VK_ENTER))
                {
                    Texto = "";
                }
                for(final Object Item : getMenu().getComponents())
                {
                    Aux1 = (JMenuItem)Item;
                    Aux1.setVisible(true);
                }
                 
                 return;

             }
           
        }
    }

    public void VerificaBotoes(final java.awt.event.KeyEvent evt)
    {
        if((evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) && CursorX!=0)
        {
            CursorX--;
        }

        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            CursorX = 0;
        }


            CursorX++;

    }

    /**
     * @return the jTextArea1
     */
    public JTextArea getjTextArea1() {
        return jTextArea1;
    }

    /**
     * @param jTextArea1 the jTextArea1 to set
     */
    public void setjTextArea1(JTextArea jTextArea1) {
        this.jTextArea1 = jTextArea1;
    }

    /**
     * @return the Palavras
     */
    public ArrayList<JMenuItem> getPalavras() {
        return Palavras;
    }

    /**
     * @param Palavras the Palavras to set
     */
    public void setPalavras(ArrayList<JMenuItem> Palavras) {
        this.Palavras = Palavras;
    }

    /**
     * @return the menu
     */
    public JPopupMenu getMenu() {
        return menu;
    }

    /**
     * @param menu the menu to set
     */
    public void setMenu(JPopupMenu menu) {
        this.menu = menu;
    }

    public boolean Vazio()
    {
        JMenuItem Aux1;
        Count = 0;

        for(final Object Item : getMenu().getComponents())
                {
                    Aux1 = (JMenuItem)Item;
                   if(Aux1.isVisible() == false)
                       Count++;
                }
        if(Count == 10)
        {
            for(final Object Item : getMenu().getComponents())
                {
                    Aux1 = (JMenuItem)Item;
                    Aux1.setVisible(true);
                }
           return true;
        }
        else
           return false;
    }

    /**
     * @return the Texto
     */
    public String getTexto() {
        return Texto;
    }

    /**
     * @param Texto the Texto to set
     */
    public void setTexto(String Texto) {
        this.Texto = Texto;
    }
}
