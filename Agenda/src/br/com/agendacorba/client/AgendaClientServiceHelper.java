package br.com.agendacorba.client;

import br.com.agendacorba.agenda.access.AgendaAccess;
import br.com.agendacorba.agenda.access.AgendaAccessHelper;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Roland on 9/15/16.
 */
public class AgendaClientServiceHelper {

    private static ORB orb;
    private static Object objNameService;
    private static NamingContext rootContext;
    private final NameComponent agendaContextName = new NameComponent("AgendaAccess", "access");
    private String nsKind = "access";
    private String[] instanceNames = {
            "agenda1",
            "agenda2",
            "agenda3"};

    private static List<NameComponent> agendaNameComp;
    private NameComponent[] compoundName;

    //TODO Compor lista com caminho pra cada Servant


    public AgendaClientServiceHelper(String[] args) {

        agendaNameComp = new ArrayList<>();
        agendaNameComp.add(new NameComponent(instanceNames[0], nsKind));
        agendaNameComp.add(new NameComponent(instanceNames[1], nsKind));
        agendaNameComp.add(new NameComponent(instanceNames[2], nsKind));

        orb = ORB.init(args, null);

        try {
            objNameService = orb.resolve_initial_references("NameService");

            rootContext = NamingContextHelper.narrow(objNameService);

        } catch (InvalidName invalidName) {
            invalidName.printStackTrace();
        }
    }

    public AgendaAccess findServer() throws NotFound {
        compoundName = null;
        Object servantRef;
        AgendaAccess agenda;
        Iterator<NameComponent> agendaI = agendaNameComp.iterator();
        agenda = null;

        while (agendaI.hasNext()) {
            compoundName = new NameComponent[]{agendaContextName, agendaI.next()};
            try {

                servantRef = rootContext.resolve(compoundName);
                agenda = AgendaAccessHelper.narrow(servantRef);
                break;

            } catch (NotFound ex) {
            } catch (org.omg.CosNaming.NamingContextPackage.InvalidName | CannotProceed ex) {
                ex.printStackTrace();
            }
        }

        if (agenda == null)
            throw new NotFound();
        else
            System.out.println(MESSAGE.SERVER_FOUND.toString() + " " + compoundName[1].id);

        return agenda;

    }

    public void unbindCurrentServer() {
        System.out.println("! Stack trace não trivialmente ocultavel!");
        System.out.println(MESSAGE.ERR_NOT_FOUND);

        try {
            rootContext.unbind(compoundName);
        } catch (NotFound notFound) {
            notFound.printStackTrace();
        } catch (CannotProceed cannotProceed) {
            cannotProceed.printStackTrace();
        } catch (org.omg.CosNaming.NamingContextPackage.InvalidName invalidName) {
            invalidName.printStackTrace();
        }

    }
}
