package br.com.agendacorba.server.backbone;

import br.com.agendacorba.agenda.Contact;
import br.com.agendacorba.agenda.access.AgendaAccess;
import br.com.agendacorba.agenda.backbone.AgendaBackbone;
import br.com.agendacorba.agenda.backbone.AgendaBackboneHelper;
import br.com.agendacorba.client.MESSAGE;
import br.com.agendacorba.server.control.ServerController;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.CosNaming.NamingContextPackage.AlreadyBound;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Roland on 9/17/16.
 */
public class AgendaBackboneServiceHelper {

    private final String namespace = "AgendaBackbone";
    private final String nsKind = "backbone";
    private String[] instanceNames = {
            "agenda1",
            "agenda2",
            "agenda3"};
    private List<NameComponent> bbNameCompList;
    private static NameComponent backboneNSComp;


    private NamingContext rootContext;
    private Object objRootCtx;
    private POA rootPOA;
    private NameComponent accessNSComp;
    private ORB orb;
    private Object objRootPOA;

    private List<AgendaBackbone> backboneServantList;
    private List<NameComponent[]> backboneServantPathList;

    private NameComponent[] compoundName;
    private String chosenInstanceName;
    private AgendaBackboneImpl backboneServant;
    private NamingContext backboneContext;

    public AgendaBackboneServiceHelper(String[] ORBargs) {
        backboneNSComp = new NameComponent(namespace, nsKind);
        backboneServantList = new ArrayList<>();
        backboneServantPathList = new ArrayList<>();
        bbNameCompList = new ArrayList<>();
        bbNameCompList.add(new NameComponent(instanceNames[0], nsKind));
        bbNameCompList.add(new NameComponent(instanceNames[1], nsKind));
        bbNameCompList.add(new NameComponent(instanceNames[2], nsKind));

        accessNSComp = new NameComponent(namespace, nsKind);

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
    }


    public void findBackboneServants() {
        compoundName = null;
        Object servantRef;
        ListIterator bbNameCompI = bbNameCompList.listIterator();

        while (bbNameCompI.hasNext()) {

            NameComponent bbNameComp = (NameComponent) bbNameCompI.next();
            compoundName = new NameComponent[]{backboneNSComp, bbNameComp};
            try {
                servantRef = rootContext.resolve(compoundName);
                backboneServantList.add(AgendaBackboneHelper.narrow(servantRef));
                backboneServantPathList.add(compoundName);
                bbNameCompI.remove();
                System.out.println(MESSAGE.SERVER_FOUND.toString() + " " + compoundName[1].id);

            } catch (NotFound ex) {
            } catch (org.omg.CosNaming.NamingContextPackage.InvalidName | CannotProceed ex) {
                ex.printStackTrace();
            }

        }
    }

    public boolean hasSiblings() {
        return !backboneServantList.isEmpty() && !this.bbNameCompList.isEmpty();
    }

    public Contact[] getCurrentList() {
        AgendaBackbone servant;
        Contact[] list = {};

        ListIterator<AgendaBackbone> servantsIt = backboneServantList.listIterator();

        while (servantsIt.hasNext()) {
            servant = servantsIt.next();
            try {
                list = servant.getAll();
                break;
            } catch (Exception e) {
                unbindServant(servant);
            }
        }


        return list;
    }

    private void unbindServant(AgendaBackbone servant) {
        unbindServant(backboneServantList.indexOf(servant));
    }

    public String getAgendaAccessInstanceName() throws Exception {
        /**
         * bbNameCompList terá os nomes disponiveis.
         */
        if (!bbNameCompList.isEmpty()) {
            this.chosenInstanceName = this.bbNameCompList.get(0).id;
            return chosenInstanceName;
        } else {
            Exception e = new Exception("No available NameComponent");
            throw e;
        }

    }

    public void buildBackboneInstanceAndBind(ServerController serverController) {

        /**
         * Só entra no if na primeira execucao do metodo
         */
        if (serverController != null)
            backboneServant = new AgendaBackboneImpl(serverController);

        try {
            Object orbObjBackbone = rootPOA.servant_to_reference(backboneServant);
            NameComponent[] bbServantName = new NameComponent[]{backboneNSComp, new NameComponent(chosenInstanceName, nsKind)};

            rootContext.rebind(bbServantName, orbObjBackbone);
            System.out.println("INFO: Backbone instance ready with name: " + chosenInstanceName + " : " + nsKind);


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
            bindBackboneContext();
            buildBackboneInstanceAndBind(null);

        } catch (AdapterInactive adapterInactive) {
            adapterInactive.printStackTrace();
        }

    }

    private void bindBackboneContext() {
        System.out.println("INFO: Creating AgendaBackbone Context");

        try {
            backboneContext = rootContext.bind_new_context(new NameComponent[]{backboneNSComp});
        } catch (NotFound |
                AlreadyBound |
                org.omg.CosNaming.NamingContextPackage.InvalidName |
                CannotProceed notFound) {
            notFound.printStackTrace();
        }

        System.out.println("INFO: Context bound with name " + namespace + " : " + nsKind);

    }

    public void notifyServants() {
        /**
         * Se nao existir outros servants conectados, retorna.
         */
        if (backboneServantList.isEmpty()) return;

        int index;
        ListIterator bbServantI = backboneServantList.listIterator();

        while (bbServantI.hasNext()) {
            index = bbServantI.nextIndex();
            AgendaBackbone servant = (AgendaBackbone) bbServantI.next();

            try {
                servant.bindDuplexConnection(chosenInstanceName);
            } catch (Exception e) {
                unbindServant(index);
            }


        }


    }

    private void unbindServant(int servantIndex) {
        {
            System.out.println("! Stack trace não trivialmente ocultavel!");
            System.out.println(MESSAGE.ERR_SERVER_NOT_FOUND);

            try {
                rootContext.unbind(backboneServantPathList.get(servantIndex));
                backboneServantList.remove(servantIndex);
            } catch (NotFound notFound) {
                notFound.printStackTrace();
            } catch (CannotProceed cannotProceed) {
                cannotProceed.printStackTrace();
            } catch (org.omg.CosNaming.NamingContextPackage.InvalidName invalidName) {
                invalidName.printStackTrace();
            }

        }
    }

    public void bindDuplexConnection(String requesterName) {
        NameComponent nameComponent = new NameComponent(requesterName, nsKind);
        NameComponent[] compoundName = new NameComponent[]{
                backboneNSComp,
                nameComponent};

        try {
            Object servantRef = rootContext.resolve(compoundName);
            backboneServantList.add(AgendaBackboneHelper.narrow(servantRef));
            backboneServantPathList.add(compoundName);

            bbNameCompList.remove(new NameComponent(requesterName, nsKind));
            System.out.println(MESSAGE.SERVER_FOUND.toString() + " " + compoundName[1].id);

        } catch (NotFound notFound) {
            notFound.printStackTrace();
        } catch (CannotProceed cannotProceed) {
            cannotProceed.printStackTrace();
        } catch (org.omg.CosNaming.NamingContextPackage.InvalidName invalidName) {
            invalidName.printStackTrace();
        }


    }

    public void propagateCreate(Contact created) {
        /**
         * Se nao existir outros servants conectados, retorna.
         */
        if (backboneServantList.isEmpty()) return;

        int index;
        ListIterator bbServantI = backboneServantList.listIterator();

        while (bbServantI.hasNext()) {
            index = bbServantI.nextIndex();
            AgendaBackbone servant = (AgendaBackbone) bbServantI.next();

            try {
                servant.propagateCreate(created);
            } catch (Exception e) {
                unbindServant(index);
            }
        }
    }

    public void propagateUpdate(Contact contact) {
        /**
         * Se nao existir outros servants conectados, retorna.
         */
        if (backboneServantList.isEmpty()) return;

        int index;
        ListIterator bbServantI = backboneServantList.listIterator();

        while (bbServantI.hasNext()) {
            index = bbServantI.nextIndex();
            AgendaBackbone servant = (AgendaBackbone) bbServantI.next();

            try {
                servant.propagateUpdate(contact);
            } catch (Exception e) {
                unbindServant(index);
            }
        }
    }

    public void propagateDelete(Contact contact) {
        {
            /**
             * Se nao existir outros servants conectados, retorna.
             */
            if (backboneServantList.isEmpty()) return;

            int index;
            ListIterator bbServantI = backboneServantList.listIterator();

            while (bbServantI.hasNext()) {
                index = bbServantI.nextIndex();
                AgendaBackbone servant = (AgendaBackbone) bbServantI.next();

                try {
                    servant.propagateDelete(contact);
                } catch (Exception e) {
                    unbindServant(index);
                }
            }
        }

    }
}
