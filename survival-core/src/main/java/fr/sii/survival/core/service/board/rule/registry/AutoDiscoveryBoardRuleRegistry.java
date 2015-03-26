package fr.sii.survival.core.service.board.rule.registry;

import fr.sii.survival.core.service.board.rule.AllowMoveRule;
import fr.sii.survival.core.service.extension.ExtensionService;
import fr.sii.survival.core.service.rule.registry.AutoDiscoveryRuleRegistry;

public class AutoDiscoveryBoardRuleRegistry extends AutoDiscoveryRuleRegistry<AllowMoveRule> implements AllowMoveRuleRegistry {

	public AutoDiscoveryBoardRuleRegistry(ExtensionService extensionService, String... packageNames) {
		super(extensionService, AllowMoveRule.class, packageNames);
	}


}