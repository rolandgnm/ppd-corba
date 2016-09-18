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

    private static ORB orb;
    private static Object objRootPOA;
    private static POA rootPOA;
    private static Object objNaming;
    private static NamingContext nameService;
    private static CalculadoraImpl calcServant;
    private static Object objRefcalc;
    private static NameComponent[] calcServName;

    public static void main(String[] args) {

        try {
            //Pega RootPOA e NameSpace
            orb = ORB.init(args, null);
            objRootPOA = null;
            objRootPOA = orb.resolve_initial_references("RootPOA");
            rootPOA = POAHelper.narrow(objRootPOA);
            objNaming = orb.resolve_initial_references("NameService");
            nameService = NamingContextHelper.narrow(objNaming);

            //Cria objeto/referencia
            calcServant = new CalculadoraImpl();
            objRefcalc = rootPOA.servant_to_reference(calcServant);

            //Cria

            //Cria nome e rebind
            calcServName = new NameComponent[]{new NameComponent("Calculadora", args[2])};
            nameService.rebind(calcServName, objRefcalc);

            System.out.printf(args[2]);
            rootPOA.the_POAManager().activate(); //Ativa e roda
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
