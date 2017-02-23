package com.sb.cdp.idl;

import com.sb.cdp.CharacterType;

public class Initialize {
    public static void initializeCharacterType() {
	CharacterType.put("Aventurier", CharacterType.Classification.CLASS,
		"Il est l’homme à tout faire. Du simple marchand à l’assassin ou au voleur.");
	CharacterType.put("Élémentaliste", CharacterType.Classification.CLASS,
		"Il a un don inné pour la magie élémentaire. À l’inverse du mage, il est capable de puiser directement dans la magie brute de la toile ce qui se traduit uniquement des éléments. L'élémentaliste a des  mouvements à faire pour lancer ses sorts. Voir le document sur les élémentalistes pour le système de sort ");
	CharacterType.put("Guerrier", CharacterType.Classification.CLASS,
		"Il est le combattant d’expérience. Celui qui voue sa vie au combat. ");
	CharacterType.put("Mage", CharacterType.Classification.CLASS,
		"Le mage est l’érudit qui étudie de longues heures pour pouvoir manier la toile de magie. Le mage doit inventer ses incantations pour lancer des sorts. Voir le document sur les mages pour le système de sort. ");
	CharacterType.put("Prêtre", CharacterType.Classification.CLASS,
		"Le prêtre répand la parole de son dieu à tout un chacun. Il doit implorer les dieux pour lancer des prières. Voir le document sur les prêtres pour le système de sort");
	CharacterType.put("Shaman", CharacterType.Classification.CLASS,
		"Il est souvent le chef de tribu indigène ou barbare. Ils représentent généralement Terra ou Ramu. Certains autres prient directement les esprits, mais tous les shamans ont un lien avec ceux-ci.");

	CharacterType.put("Humain", CharacterType.Classification.RACE,
		"Représente la grande majorité du continent de Solunne. Il peut s'adapter à tout style de vie qu'elle soit sur terre ou sur mer.");
	CharacterType.put("Barbare", CharacterType.Classification.RACE,
		"Créature plutôt de type guerrier. Bataille et guerre sont les mots préférés de leur vocabulaire. Ils sont sanguinaires, cruels, étranges et généralement incultes. Il semble par contre que quelques barbares aient apprivoisé quelque peut le monde des humains civilisés du continent créant dans leurs rangs de nouvelles formes de passe-temps autre que la baston. ");
	CharacterType.put("Elfe", CharacterType.Classification.RACE,
		"Généralement de taille mince, imberbe (sans barbe) et ont les oreilles pointues. Ils sont d'un tempérament plutôt noble et leur sagesse est sans égal. On les retrouve généralement au sud du continent de Solunne. *Il faut des oreilles pointues   ");
	CharacterType.put("Elfe noir", CharacterType.Classification.RACE,
		"Les drows sont des elfes ayant reçu un sort jeté par Zora suite à leur révolte envers les dieux. Ce sort les fit devenir tout noir. Ils ne peuvent plus supporter la lumière du jour ce qui fait d'eux des êtres plutôt discrets le jour. Ils sont très actifs la nuit. Les priants de Zora ne supportent pas leur présence.   *Il faut porter des oreilles pointues et être peint en noir. De plus, les elfes noirs doivent se protéger du soleil pendant la journée. ");
	CharacterType.put("Gitan", CharacterType.Classification.RACE,
		"Cette race ne peut être choisie que par l’autorisation du maître de jeu. Le background des gitans peut être complexe et facile à la fois. Ils ont une mission commune et mystérieuse connue que par eux. Ils sont 5 familles vivantes dans un régime matriarcal, la notion de père n'existe pas et tous les membres d'une famille ont la même mère, la matriarche. Alors un joueur peut acheter l'une des habiletés du gitan, mais peut aussi choisir un métier, une profession ou une classe,mais en se faisant il dilue son pouvoir d'origine. Seul un joueur expérimenté à l'île des légendes peut choisir cette classe. *Il faut porter minimalement la couleur de sa famille. Un gitan doit absolument avoir une famille. ");
	CharacterType.put("Hybride", CharacterType.Classification.RACE,
		"Ils sont souvent le résultat du mélange de deux races. Le plus souvent, ils sont à moitié humaine et à moitié une bête. Un déguisement adéquat est requis selon l’animal choisi. *Exemple : fourrure, crocs, griffes, etc.");
	CharacterType.put("Orc", CharacterType.Classification.RACE,
		"Créature à la peau verdâtre, il est costaud et doté de quelques grosses dents qui lui sortent de la bouche. D'une intelligence plutôt primitive, il est un bon chasseur et sa force musculaire fait de lui un puissant allié lors des batailles. Il se nourrit et survit avec ce que la forêt peut lui fournir. Un écu peut lui paraître aussi banal qu'une pierre au sol. *Être peint en vert et/ou gris. ");
    }
    
    public 
}
