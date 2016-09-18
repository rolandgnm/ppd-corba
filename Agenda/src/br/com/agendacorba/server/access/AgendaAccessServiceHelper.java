package br.com.agendacorba.server.access;

import br.com.agendacorba.server.control.ServerController;
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

/**
 * Created by Roland on 9/17/16.
 */
public class AgendaAccessServiceHelper {


    private NamingContext rootContext;
    private Object objRootCtx;
    private POA rootPOA;
    private Object objRootPOA;

    private String instanceName;
    private AgendaAccessImpl accessServant;
    private Object orbObjAgenda;
    private String namespace = "AgendaAccess";
    private String nsKind = "access";
    String refName;
    private NamingContext accessContext;
    private ORB orb;

    public AgendaAccessServiceHelper(String instanceName, String[] ORBargs) {
        this.instanceName = instanceName;

        try {
            orb = ORB.init(ORBargs, null);
            objRootPOA = null;
            objRootPOA = orb.resolve_initial_references("RootPOA");
            rootPOA = POAHelper.narrow(objRootPOA);
            objRootCtx = orb.resolve_initial_references("NameService");
            rootContext = NamingContextHelper.narrow(objRootCtx);
        } catch (InvalidName invalidName) {
            invalidName.printStackTrace();
        }

        findOrBindNameContext(namespace, nsKind);
    }

    private void findOrBindNameContext(String namespace, String nsKind) {
        NameComponent[] accessNSComp = new NameComponent[]{new NameComponent(namespace, nsKind)};
        try {

            rootContext.rebind_context(accessNSComp, accessContext);

        } catch (NotFound notFound) {
            notFound.printStackTrace();
        } catch (CannotProceed cannotProceed) {
            cannotProceed.printStackTrace();
        } catch (org.omg.CosNaming.NamingContextPackage.InvalidName invalidName) {
            invalidName.printStackTrace();
        }

    }

    public void buildServantAndBind(ServerController serverController) {
        accessServant = new AgendaAccessImpl(serverController);

        try {

            Object orbObjAccess = rootPOA.servant_to_reference(accessServant);
            NameComponent[] servantName = new NameComponent[]{new NameComponent(instanceName, nsKind)};

            accessContext.rebind(servantName, orbObjAccess);

            rootPOA.the_POAManager().activate();
            orb.run();

        } catch (ServantNotActive servantNotActive) {
            servantNotActive.printStackTrace();
        } catch (WrongPolicy wrongPolicy) {
            wrongPolicy.printStackTrace();
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
