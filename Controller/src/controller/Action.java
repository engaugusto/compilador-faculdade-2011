/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;



import java.io.*;
import model.*;


/*Interface contendo um método abstrado, que será implementado pelas classes que irão atender as
 solicitações requisitadas*/
public interface Action {
    public void execute(vmTela dados)
        throws IOException;
}
