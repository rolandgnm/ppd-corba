package br.com.corbatest.cliente;

import Matematica.Calculadora;
import Matematica.CalculadoraHelper;
import Matematica.DivisaoPorZero;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

/**
 * Created by Roland on 9/13/16.
 */
public class Cliente {

    private static ORB orb;
    private static Object objNameService;
    private static NamingContext naming;
    private static NameComponent[] name;
    private static Object objRefToServant;
    private static Calculadora calc;

    public static void main(String[] args) {
        orb = ORB.init(args, null);

        try {
            objNameService = orb.resolve_initial_references("NameService");

            naming = NamingContextHelper.narrow(objNameService);

            name = new NameComponent[]{new NameComponent("Calculadora", args[2])};
            System.out.printf(args[2]);

            objRefToServant = naming.resolve(name);

            calc = CalculadoraHelper.narrow(objRefToServant);

            System.out.println("5+3=" + calc.soma(5, 3));

            while (1 != 0) {
                Thread.sleep(1000);
                try {
                    System.out.printf("5/0=" + calc.divisao(5, 0));
                } catch (DivisaoPorZero ex) {
                    System.out.println("Divisao por Zero");
                    System.out.println("A divisao foi " + ex.arg1 +
                            "/" + ex.arg2);
                }
            }

        } catch (InvalidName invalidName) {
            invalidName.printStackTrace();
        } catch (CannotProceed cannotProceed) {
            cannotProceed.printStackTrace();
        } catch (org.omg.CosNaming.NamingContextPackage.InvalidName invalidName) {
            invalidName.printStackTrace();
        } catch (NotFound notFound) {
            notFound.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (1 != 0) ;
    }


}
