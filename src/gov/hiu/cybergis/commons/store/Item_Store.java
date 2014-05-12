package gov.hiu.cybergis.commons.store;

import gov.hiu.cybergis.commons.interfaces.HTMLInterface;
import gov.hiu.cybergis.commons.interfaces.JSONInterface;
import gov.hiu.cybergis.commons.interfaces.PageInterface;
import gov.hiu.cybergis.commons.interfaces.URLInterface;
import gov.hiu.cybergis.commons.interfaces.XMLInterface;
import gov.hiu.cybergis.commons.registry.Item_Registry;

public interface Item_Store<A> extends Item_Registry<A>, JSONInterface, XMLInterface, HTMLInterface, URLInterface, PageInterface
{
}
