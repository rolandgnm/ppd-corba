package br.com.corbatest.servidor;

import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

public class Servidor {

    public static void main(String[] args) {

        try {
            ORB orb = ORB.init(args, null);

            Object objPOA = null;
            objPOA = orb.resolve_initial_references("RootPOA");
            POA rootPOA = POAHelper.narrow(objPOA);

            Object obj =  orb.resolve_initial_references("NameService");
            NamingContext naming = NamingContextHelper.narrow(obj);

            CalculadoraImpl calcServant = new CalculadoraImpl();

            Object objRef = rootPOA.servant_to_reference(calcServant);

            NameComponent[] name = {new NameComponent("Calculadora", args[2])};
            naming.rebind(name, objRef);
            System.out.printf(args[2]);

            rootPOA.the_POAManager().activate();

            System.out.printf("Servidor Pronto ...");

            orb.run();

        } catch (InvalidName invalidName) {
            invalidName.printStackTrace();
        } catch (WrongPolicy wrongPolicy) {
            wrongPolicy.printStackTrace();
        } catch (ServantNotActive servantNotActive) {
            servantNotActive.printStackTrace();
        } catch (CannotProceed cannotProceed) {
            cannotProceed.printStackTrace();
        } catch (org.omg.CosNaming.NamingContextPackage.InvalidName invalidName) {
            invalidName.printStackTrace();
        } catch (NotFound notFound) {
            notFound.printStackTrace();
        } catch (AdapterInactive adapterInactive) {
            adapterInactive.printStackTrace();
        }
    }
}
