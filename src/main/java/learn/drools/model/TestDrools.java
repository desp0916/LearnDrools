package learn.drools.model;

import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.model.KieBaseModel;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.builder.model.KieSessionModel;
import org.kie.api.conf.EqualityBehaviorOption;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.conf.ClockTypeOption;

public class TestDrools {

	public static void main(String[] args) {
		KieServices kieServices = KieServices.Factory.get();
		KieModuleModel kieModuleModel = kieServices.newKieModuleModel();

		KieBaseModel kieBaseModel1 = kieModuleModel.newKieBaseModel("KBase1")
				.setDefault(true)
				.setEqualsBehavior(EqualityBehaviorOption.EQUALITY)
				.setEventProcessingMode(EventProcessingOption.STREAM);

		KieSessionModel ksessionModel1 = kieBaseModel1.newKieSessionModel("KsSession1")
				.setDefault(true)
				.setType(KieSessionModel.KieSessionType.STATEFUL)
				.setClockType(ClockTypeOption.get("realtime"));

		KieFileSystem kfs = kieServices.newKieFileSystem();
//		kfs.writeKModuleXML(kieModuleModel.toXML());
		kfs.write("src/main/resources/learn/drools/kmodule1.xml", kieModuleModel.toXML());
		System.out.println(kieModuleModel.toXML());
	}
}
