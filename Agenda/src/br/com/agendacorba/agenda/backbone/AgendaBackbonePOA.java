package br.com.agendacorba.agenda.backbone;


/**
* br/com/agendacorba/agenda/backbone/AgendaBackbonePOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Agenda.idl
* Monday, September 19, 2016 11:31:05 PM BRT
*/

public abstract class AgendaBackbonePOA extends org.omg.PortableServer.Servant
 implements br.com.agendacorba.agenda.backbone.AgendaBackboneOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("propagateCreate", new java.lang.Integer (0));
    _methods.put ("getAll", new java.lang.Integer (1));
    _methods.put ("propagateUpdate", new java.lang.Integer (2));
    _methods.put ("propagateDelete", new java.lang.Integer (3));
    _methods.put ("bindDuplexConnection", new java.lang.Integer (4));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // br/com/agendacorba/agenda/backbone/AgendaBackbone/propagateCreate
       {
         br.com.agendacorba.agenda.Contact newContact = br.com.agendacorba.agenda.ContactHelper.read (in);
         this.propagateCreate (newContact);
         out = $rh.createReply();
         break;
       }

       case 1:  // br/com/agendacorba/agenda/backbone/AgendaBackbone/getAll
       {
         br.com.agendacorba.agenda.Contact $result[] = null;
         $result = this.getAll ();
         out = $rh.createReply();
         br.com.agendacorba.agenda.contactListHelper.write (out, $result);
         break;
       }

       case 2:  // br/com/agendacorba/agenda/backbone/AgendaBackbone/propagateUpdate
       {
         br.com.agendacorba.agenda.Contact updatedContact = br.com.agendacorba.agenda.ContactHelper.read (in);
         this.propagateUpdate (updatedContact);
         out = $rh.createReply();
         break;
       }

       case 3:  // br/com/agendacorba/agenda/backbone/AgendaBackbone/propagateDelete
       {
         br.com.agendacorba.agenda.Contact deletedContact = br.com.agendacorba.agenda.ContactHelper.read (in);
         this.propagateDelete (deletedContact);
         out = $rh.createReply();
         break;
       }

       case 4:  // br/com/agendacorba/agenda/backbone/AgendaBackbone/bindDuplexConnection
       {
         String requesterName = in.read_string ();
         this.bindDuplexConnection (requesterName);
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:br/com/agendacorba/agenda/backbone/AgendaBackbone:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public AgendaBackbone _this() 
  {
    return AgendaBackboneHelper.narrow(
    super._this_object());
  }

  public AgendaBackbone _this(org.omg.CORBA.ORB orb) 
  {
    return AgendaBackboneHelper.narrow(
    super._this_object(orb));
  }


} // class AgendaBackbonePOA
