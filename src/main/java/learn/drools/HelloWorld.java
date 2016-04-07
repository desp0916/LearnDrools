package learn.drools;

import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class HelloWorld {

	public static void main(String[] args) {
		KieServices ks = KieServices.Factory.get();
		KieContainer kc = ks.getKieClasspathContainer();
		execute(kc);
	}

	public static void execute(KieContainer kc) {
		KieSession ksession = kc.newKieSession("HelloWorldKs");
//        ksession.setGlobal( "list",
//                new ArrayList<Object>() );
        ksession.addEventListener( new DebugAgendaEventListener() );
        ksession.addEventListener( new DebugRuleRuntimeEventListener() );
		final Message message = new Message();
		message.setMessage("Hello World");
		message.setStatus(Message.HELLO);
		ksession.insert(message);
		ksession.fireAllRules();
		ksession.dispose();
	}

	public static class Message {

		public static final int HELLO = 0;
		public static final int GOODBYE = 1;
		private String message;
		private int status;

		public Message() {
		}

		public String getMessage() {
			return this.message;
		}

		public void setMessage(final String message) {
			this.message = message;
		}

		public int getStatus() {
			return this.status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

        public static Message doSomething(Message message) {
            return message;
        }

        public boolean isSomething(String msg,
                                   List<Object> list) {
            list.add( this );
            return this.message.equals( msg );
        }

	}
}