/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;

/**
 *
 * @author Queiroz
 */
public class TokenValue {

    private String _tokenName;
    private Tipo _Type;
    private boolean _Declarado;
    private Object _value;
    private boolean _usedOnce;
    private int _end_assembly;

    /**
     * @return the _usedOnce
     */
    public boolean isUsedOnce() {
        return _usedOnce;
    }

    /**
     * @param usedOnce the _usedOnce to set
     */
    public void setUsedOnce(boolean usedOnce) {
        this._usedOnce = usedOnce;
    }

    public String getEndereco() {
        return String.valueOf(_end_assembly);
    }
    
    public void setAssemblyAddress(int endereco){
        this._end_assembly = endereco;
    }

    public enum Tipo { INTEGER, REAL, NONE};
    
    public TokenValue() {
        this._end_assembly = -1;
    }

    public TokenValue(String tokenName,Tipo type, Object Valor, boolean Declarada) {
        this._Type = type;
        this._tokenName = tokenName;
        this._value = Valor;
        this._usedOnce = false;
        this._end_assembly = -1;
    }

    /**
     * @return the _tokenName
     */
    public String getTokenName() {
        return _tokenName;
    }

    /**
     * @param tokenName the _tokenName to set
     */
    public void setTokenName(String tokenName) {
        this._tokenName = tokenName;
    }


    /**
     * @return the _Declarado
     */
    public boolean isDeclarado() {
        return _Declarado;
    }

    /**
     * @param Declarado the _Declarado to set
     */
    public void setDeclarado(boolean Declarado) {
        this._Declarado = Declarado;
    }

    /**
     * @return the _Type
     */
    public Tipo getType() {
        return _Type;
    }

    /**
     * @param Type the _Type to set
     */
    public void setType(Tipo Type) {
        this._Type = Type;
    }

    /**
     * @return the _value
     */
    public boolean isInteger() {
        return (this._value instanceof Integer);
    }
    
    public boolean isReal(){
        return (this._value instanceof Float);
    }
}
