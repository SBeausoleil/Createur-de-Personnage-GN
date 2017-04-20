package com.sb.cdp.idl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.sb.cdp.CharacterType;
import com.sb.cdp.CharacterTypePool;
import com.sb.cdp.Library;
import com.sb.cdp.RPG;
import com.sb.cdp.ability.Ability;
import com.sb.cdp.magic.Domain;
import com.sb.cdp.magic.DomainLibrary;
import com.sb.cdp.magic.God;
import com.thoughtworks.xstream.XStream;

public class Initializer {

    public static final String PRAYER = "Prière";
    public static final String SPELL = "Sort";

    public static final String ABILITY_LIBRARY = "Générale";

    public static RPG initialize() throws FileNotFoundException, IOException {
	RPG idl = new RPG("Île Des Légendes"); // IDL: Ile Des Legendes
	idl.getParameters().setAbilityPointsPerLevel(1);
	idl.getParameters().setStartingAbilityPoints(10);
	idl.getParameters().setXpPerEvent(5);
	idl.getParameters().setXpPerLevel(10);
	
	idl.setCharacterTypes(characterType());
		//Library<String, Ability> abilities = readAbilities(idl.getCharacterTypes());
		//idl.getAbilityLibraries().put(abilities.getName(), abilities);
	idl.setAbilityLibraries(readXMLAbilities());
	idl.setGods(gods());
	//DomainLibrary spellDomains = spellDomains();
	//readSpells(spellDomains);
	//idl.registerDomainLibrary(spellDomains);
	//DomainLibrary prayerDomains = prayerDomains(idl.getGods().values());
	//idl.registerDomainLibrary(prayerDomains);
	Collection<Set<DomainLibrary>> domains = readXMLDomainLibraries().values();
	for (Set<DomainLibrary> set : domains) {
	    for (DomainLibrary lib : set) {
		idl.registerDomainLibrary(lib);
	    }
	}
	return idl;
    }

    public static DomainLibrary prayerDomains(Iterable<God> gods) {
	DomainLibrary domains = new DomainLibrary("Prières publiques", PRAYER);
	// Start by registering the already existing domains within the gods
	for (God god : gods)
	    for (Domain domain : god.getDomains())
		domains.put(domain.getName(), domain);

	domains.put("Guérison", new Domain("Guérison", PRAYER));
	domains.put("Bénédiction", new Domain("Bénédiction", PRAYER));
	domains.put("Protection", new Domain("Protection", PRAYER));
	domains.put("Divination", new Domain("Divination", PRAYER));
	return domains;
    }

    public static DomainLibrary spellDomains() {
	DomainLibrary domains = new DomainLibrary("Sorts publiques", SPELL);
	domains.put("Évocation", new Domain("Évocation", SPELL,
		"La magie d’évocation canalise l’énergie magique en une force d’attaque. L’évocateur est le spécialiste des attaques puissantes à distance ou au touché qu’elle soit dirigé envers une personne ou un groupe. L’évocateur  peut aussi devenir un combattant de mêlée redoutable. Que ce soit les améliorations d’armes, l’amélioration des capacités de combat ou tout ce qui peut aider aux guerriers pendant un combat."));
	domains.put("Protection", new Domain("Protection", SPELL,
		"Cette école offre des sorts de protections contre les attaques physiques, mentales et magiques pour une personne ou un groupe. "));
	domains.put("Nécromancie", new Domain("Nécromancie", SPELL,
		"Les nécromanciens utilisent les énergies magiques pour contrôler une entité morte, sans utiliser les pouvoirs de Narzul."));
	domains.put("Divination", new Domain("Divination", SPELL,
		"Cette école permet de voir, de prédire ou d’influencer certaines choses que le commun des mortels ne peut contrôler."));
	domains.put("Enchantement", new Domain("Enchantement", SPELL,
		"L’école de l’enchantement permet d’influencer et de soumettre l’esprit d’être vivant selon la volonté du magicien. Elle permet aussi d’enchanter des objets."));
	domains.put("Général", new Domain("Général", SPELL,
		"Cette école  englobe tout ce qui n’a aucun rapport avec les autres magies."));
	return domains;
    }

    public static Map<String, Domain> readSpells(Map<String, Domain> domains)
	    throws FileNotFoundException, IOException {
	// Will be changed later to read from a Json document. However until that will not be ready until the whole data structure of the application is set in stone.
	domains = SpellParser.parseSpells(new File("IDL_Spells.txt"), domains);
	return domains;
    }

    public static CharacterTypePool characterType() {
	CharacterTypePool ctPool = new CharacterTypePool();
	ctPool.put("Aventurier", CharacterType.Classification.CLASS,
		"Il est l’homme à tout faire. Du simple marchand à l’assassin ou au voleur.");
	ctPool.put("Élémentaliste", CharacterType.Classification.CLASS,
		"Il a un don inné pour la magie élémentaire. À l’inverse du mage, il est capable de puiser directement dans la magie brute de la toile ce qui se traduit uniquement des éléments. L'élémentaliste a des  mouvements à faire pour lancer ses sorts. Voir le document sur les élémentalistes pour le système de sort ");
	ctPool.put("Guerrier", CharacterType.Classification.CLASS,
		"Il est le combattant d’expérience. Celui qui voue sa vie au combat. ");
	ctPool.put("Mage", CharacterType.Classification.CLASS,
		"Le mage est l’érudit qui étudie de longues heures pour pouvoir manier la toile de magie. Le mage doit inventer ses incantations pour lancer des sorts. Voir le document sur les mages pour le système de sort. ");
	ctPool.put("Prêtre", CharacterType.Classification.CLASS,
		"Le prêtre répand la parole de son dieu à tout un chacun. Il doit implorer les dieux pour lancer des prières. Voir le document sur les prêtres pour le système de sort");
	ctPool.put("Shaman", CharacterType.Classification.CLASS,
		"Il est souvent le chef de tribu indigène ou barbare. Ils représentent généralement Terra ou Ramu. Certains autres prient directement les esprits, mais tous les shamans ont un lien avec ceux-ci.");

	ctPool.put("Humain", CharacterType.Classification.RACE,
		"Représente la grande majorité du continent de Solunne. Il peut s'adapter à tout style de vie qu'elle soit sur terre ou sur mer.");
	ctPool.put("Barbare", CharacterType.Classification.RACE,
		"Créature plutôt de type guerrier. Bataille et guerre sont les mots préférés de leur vocabulaire. Ils sont sanguinaires, cruels, étranges et généralement incultes. Il semble par contre que quelques barbares aient apprivoisé quelque peut le monde des humains civilisés du continent créant dans leurs rangs de nouvelles formes de passe-temps autre que la baston. ");
	ctPool.put("Elfe", CharacterType.Classification.RACE,
		"Généralement de taille mince, imberbe (sans barbe) et ont les oreilles pointues. Ils sont d'un tempérament plutôt noble et leur sagesse est sans égal. On les retrouve généralement au sud du continent de Solunne. *Il faut des oreilles pointues   ");
	ctPool.put("Elfe noir", CharacterType.Classification.RACE,
		"Les drows sont des elfes ayant reçu un sort jeté par Zora suite à leur révolte envers les dieux. Ce sort les fit devenir tout noir. Ils ne peuvent plus supporter la lumière du jour ce qui fait d'eux des êtres plutôt discrets le jour. Ils sont très actifs la nuit. Les priants de Zora ne supportent pas leur présence.   *Il faut porter des oreilles pointues et être peint en noir. De plus, les elfes noirs doivent se protéger du soleil pendant la journée. ");
	ctPool.put("Gitan", CharacterType.Classification.RACE,
		"Cette race ne peut être choisie que par l’autorisation du maître de jeu. Le background des gitans peut être complexe et facile à la fois. Ils ont une mission commune et mystérieuse connue que par eux. Ils sont 5 familles vivantes dans un régime matriarcal, la notion de père n'existe pas et tous les membres d'une famille ont la même mère, la matriarche. Alors un joueur peut acheter l'une des habiletés du gitan, mais peut aussi choisir un métier, une profession ou une classe,mais en se faisant il dilue son pouvoir d'origine. Seul un joueur expérimenté à l'île des légendes peut choisir cette classe. *Il faut porter minimalement la couleur de sa famille. Un gitan doit absolument avoir une famille. ");
	ctPool.put("Hybride", CharacterType.Classification.RACE,
		"Ils sont souvent le résultat du mélange de deux races. Le plus souvent, ils sont à moitié humaine et à moitié une bête. Un déguisement adéquat est requis selon l’animal choisi. *Exemple : fourrure, crocs, griffes, etc.");
	ctPool.put("Orc", CharacterType.Classification.RACE,
		"Créature à la peau verdâtre, il est costaud et doté de quelques grosses dents qui lui sortent de la bouche. D'une intelligence plutôt primitive, il est un bon chasseur et sa force musculaire fait de lui un puissant allié lors des batailles. Il se nourrit et survit avec ce que la forêt peut lui fournir. Un écu peut lui paraître aussi banal qu'une pierre au sol. *Être peint en vert et/ou gris. ");

	return ctPool;
    }

    public static Library<String, Ability> readAbilities(CharacterTypePool ctPool)
	    throws FileNotFoundException, IOException {
	Library<String, Ability> lib = new Library<>(ABILITY_LIBRARY, String.class, Ability.class);
	// Will be changed later to read from a Json document. However until that will not be ready until the whole data structure of the application is set in stone.
	AbilityParser.parseAbilities(new File("IDL_Inline_Abilities.txt"), lib, ctPool);
	lib.setPublic(true);
	return lib;
    }

    public static Map<String, Library<String, Ability>> readXMLAbilities() {
	XStream xml = new XStream();
	return (Map<String, Library<String, Ability>>) xml.fromXML(new File("IDL_Abilities.xml"));
    }
    
    public static Map<String, Set<DomainLibrary>> readXMLDomainLibraries() {
	XStream xml = new XStream();
	return (Map<String, Set<DomainLibrary>>) xml.fromXML(new File("IDL_Spells.xml"));
    }
    
    public static Map<String, God> gods() {
	Map<String, God> gods = new TreeMap<>();

	gods.put("Zora", new God("Zora", new Domain[] { new Domain("Soleil", PRAYER) }, "Un soleil",
		"Déesse de la lumière, du jour, du renouveau, de la force, de la semence et de la vie, de la chaleur, et de beaucoup d'autres choses aussi. Elle est représentée par un soleil, déesse créatrice de Solunne et de la moitié de ses habitants.En couple avec son égal, Uter, qui ont résolu depuis l'an 1207 le conflit qui les opposaient.Ses fidèles sont neutres à bon. Jadis il y avait une branche secrète qui était \"evil\", mais les rumeurs disent qu'elle n'existent plus aujourd'hui."));
	gods.put("Uter", new God("Uter", new Domain[] { new Domain("Nuit", PRAYER) }, "Une lune",
		"Dieu de la nuit et du mystère, de l'oeil qui veille, de la passion, du sommeil, du renouveau, de la naissance, de l'imaginaire, du pouvoir. Il est représenté par la lune. Ses fidèles sont neutres à bons. Jadis, tout comme pour Zora, il existait une branche secrète \"évil\" et qui a disparue de nos jours."));
	gods.put("Éode", new God("Éode", new Domain[] { new Domain("Neutralité", PRAYER) }, "Une étoile",
		"Dieu de la neutralité, de la vie, de l'équilibre, de la justice, de la philosophie, des arts, des sciences, de la nature et de sa protection. Éode est représenté par une étoile à 4 branches. On voit souvent une épée au centre de l'étoile à 4 branches. "));
	gods.put("Ayka", new God("Ayka", new Domain[] { new Domain("Souffrance", PRAYER) },
		"Visage bleu d'une femme criant et souffrant ",
		"Déesse de la souffrance, des supplices, des mensonges, de la traîtrise, de la confusion.Ses fidèles se cachent aux yeux de tous. Ils sont plutôt chaotiques dans leurs attitudes. Ils recherchent la souffrance autant pour eux que pour les autres, cette souffrance les comble. Elle ne cherche pas à tuer, mais plutôt à faire souffrir. Plusieurs sages pensent qu'elle tire son pouvoir de la souffrance ainsi produit. La couleur bleue la représente assez bien. On reconnaît l'existence de plusieurs artefacts puissants qu'elle aurait fabriqués dans un seul but, faire souffrir. Ils sont facilement identifiables. Son ascension comme déesse est plutôt nébuleuse. Personne ne sait d'où elle vient."));
	gods.put("Ramu", new God("Ramu", new Domain[] { new Domain("Guerre", PRAYER) }, "Une tête de granit",
		"Dieu de la guerre, des géants, des barbares et de plusieurs races de nains. Il est souvent représenté par une tête de granite.On sait que jadis Ramu était un barbare qui s'est levé contre la cruauté qu'une armée s'apprêtait à faire subir à un village sans défense. Il se serait interposé entre l'armée et le village et se serait battu avec tellement de vigueur que les dieux en ont été touchés par un tel acte d'héroïsme. Lors de sa mort inévitable, son corps aurait disparu. Le temps de la bataille a permis aux habitants de fuir le village et ainsi éviter une mort certaine. Depuis ce temps, les barbares chantent les louanges de leur héros. On raconte qu'Il est toujours là, dans les Cieux, et qui attend... d'aider les héros."));
	gods.put("Zoter", new God("Zoter", new Domain[] { new Domain("Chaos", PRAYER) }, "Une spirale",
		"Dieu du conflit et du chaos.Il est né de l'Union d'Uter et de Zora lors de la fin du conflit. Une énergie en a émergé donnant naissance à deux divinités, Zoter et sa soeur Terra. Il a été endormi sur l'île des légendes à cause de nombreuses actions des plus cruelles et des plus destructrices. Il est souvent vu comme un jeune garçon crieur et chialeur qui passe son temps à injurier les gens autour de lui. Il est le Chaos. On ne compte pas de prêtre à son service, s'amusant plutôt à voir ses adeptes mourir pour sa cause, créer le Chaos."));
	gods.put("Terra", new God("Terra", new Domain[] { new Domain("Nature", PRAYER) }, "Une feuille d'hêtre",
		"Déesse de la protection, déesse gardienne dite la Sauveuse. Elle est tout à l'opposé de son frère Zoter. Pour éviter une guerre avec les mages des tours noirs et blanches elle a été endormi tout prêt de son frère. Elle est la gardienne du repos de Zoter. Elle est surtout associée à l'Île des Légendes, lieu de son seul temple. On raconte qu'elle aurait laissé des secrets protégés par des druides. Elle est souvent identifiée comme une guerrière en armure complète, l'épée à la main, les cheveux au vent. On dit aussi que son visage change selon les événements. On raconte qu'Éode l'aide beaucoup, mais ce ne sont que des rumeurs."));
	gods.put("Narzul", new God("Narzul", new Domain[] { new Domain("Mort", PRAYER) }, "Une faux",
		"Dieu de la mort. Il est souvent vu comme un homme sombre et encapuchonné d'une cape noire. On ne sait rien de lui. Il a souvent été appelé ''le messager du grand Dormeur''. Nul ne sait d'où il vient et comment il est venu dans ce monde. Avant l'événement de l'Île, personne ne le connaissait et les autres dieux avaient le contrôle total sur la mort. Depuis la guerre de l'Île, en l'an de grâce 2007 du mois d'octobre, ce dieu est devenu maître de la mort. Il a le contrôle de ce site étrange et met en échec toutes les armées que l'Union (les forces de Zora et d'Uter réuni) envoie contre lui. Il a pris possession de la moitié du royaume de Zalte et l'autre ne ressemble qu'à un vaste champ de désolation créé par d'innombrables combats. L'Union a réussi à éviter à tous les Zoratiens et les Uteriens de tomber sous son pouvoir lors de la mort de l'un d'eux. Mais les autres...On sait qu'il recherche quelque chose et qu'il est furieux de ne pas  avoir pu le trouver sur l'Île. On dit que sa colère est tel qu'il a l'intention de tout détruire sur les terres de Solunne. En espérant que les forces de l'Union puissent un jour le détruire. (Information datant de l'an de grâce 2009). Les armées de Narzul ont été enfin vaincues. On raconte que des héros auraient réussi à le vaincre en utilisant un artefact puissant. Nous savons, tout de même, que plusieurs de ses lieutenants ont réussi à se sauver, promettant de retrouver leur Dieu et d'en finir avec les humains et Éode. "));
	gods.put("Mentier", new God("Mentier", new Domain[] { new Domain("Temps", PRAYER) }, "Un Sablier",
		"Dieu du temps. Mentier est un nouveau dieu qui est apparu depuis peu de temps. On raconte qu'il aurait été emprisonné dans le monde de Solunne et qu'il aurait dernièrement réussit à se sortir de sa prison. C'est pourquoi on ne sait pas grand chose de lui, sauf qu'il se présente comme le dieu du temps."));
	//gods.put("Gaé", new God("Gaé", new Domain[] { new Domain("Vie") }, "Une fougère", ""));

	return gods;
    }

}
