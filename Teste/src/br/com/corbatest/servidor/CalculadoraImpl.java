package br.com.corbatest.servidor;

import Matematica.CalculadoraPOA;
import Matematica.DivisaoPorZero;

/**
 * Created by Roland on 9/13/16.
 */
public class CalculadoraImpl extends CalculadoraPOA {

    @Override
    public float soma(float arg1, float arg2) {
        System.out.println("Soma = " + arg1 + " + " + arg2);
        return arg1 + arg2;
    }

    @Override
    public float divisao(float arg1, float arg2) throws DivisaoPorZero {

        System.out.println("Divisao =" + arg1 + "/" + arg2);
        if (arg2 == 0)
            throw new DivisaoPorZero(arg1, arg2);
        return arg1 / arg2;
    }
}