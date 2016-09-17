package br.com.agendacorba.client;

import br.com.agendacorba.agenda.access.AgendaAccess;
import br.com.agendacorba.agenda.access.AgendaAccessHelper;
import jdk.nashorn.internal.ir.annotations.Immutable;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import javax.lang.model.element.Name;
import javax.management.ImmutableDescriptor;
import java.util.*;

/**
 * Created by Roland on 9/15/16.
 */
public class AgendaClientServiceHelper {

    private static ORB orb;
    private static Object objNameService;
    private static NamingContext rootContext;
    private Object objRefToServant;
    private final NameComponent agendaContextName = new NameComponent("AgendaAccess", "server");
    private AgendaAccess agendaStub;

    private static List<NameComponent> agendaNames;

    //TODO Compor lista com caminho pra cada Servant


    public AgendaAccess findServer() {
        NameComponent[] compoundName;
        Object servantRef;
        AgendaAccess agenda;
        Iterator<NameComponent> agendaI = agendaNames.iterator();
        agenda = null;

        while (agendaI.hasNext()) {
            compoundName = new NameComponent[]{agendaContextName, agendaI.next()};
            try {

                servantRef = rootContext.resolve(compoundName);
                agenda = AgendaAccessHelper.narrow(servantRef);

            } catch (NotFound notFound) {
                continue;
            } catch (CannotProceed cannotProceed) {
                continue;
            } catch (org.omg.CosNaming.NamingContextPackage.InvalidName invalidName) {
                continue;
            }
        }

        return agenda;

    }


    public AgendaClientServiceHelper(String[] args) {

        agendaNames = new ArrayList<>();
        agendaNames.add(new NameComponent("agenda1", "agenda"));
        agendaNames.add(new NameComponent("agenda2", "agenda"));
        agendaNames.add(new NameComponent("agenda3", "agenda"));

        orb = ORB.init(args, null);

        try {
            objNameService = orb.resolve_initial_references("NameService");

            rootContext = NamingContextHelper.narrow(objNameService);

        } catch (InvalidName invalidName) {
            invalidName.printStackTrace();
        }
    }

}
