package test;

import java.util.Map;
import java.util.TreeMap;

import com.sb.cdp.Library;
import com.sb.cdp.magic.Domain;
import com.sb.cdp.magic.Spell;

public class Test {

    public static void main(String[] args) {
	Domain<Spell> spells = new Domain<>("spells");
	test(spells);

	Map<String, Domain> map = new TreeMap<>();
	map.put(spells.getName(), spells);

	Library<String, Domain<Spell>> library = new Library<>("Library");
	library.put(spells.getName(), spells);

	Map<String, Library<String, Domain<Spell>>> mapLibrary = new TreeMap<>();
	mapLibrary.put(library.getName(), library);
    }

    public static void test(Domain domain) {
	System.out.println(domain.getName());
    }
}
