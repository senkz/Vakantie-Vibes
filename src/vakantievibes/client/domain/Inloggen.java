package vakantievibes.client.domain;


public class Inloggen {
	static String salt = "VakantieVibes";
	static VakantieVibes ServiceImpl;
	
	public Inloggen() {}
	
	public Inloggen(VakantieVibes sI) {
		ServiceImpl = sI;
	}

	public Gebruiker Login(String ww, String gb) {
		System.out.println("*********************");
		System.out.println("Gebruikersnaam:" + gb);
		System.out.println("Wachtwoord pre-hash:" + ww);
		System.out.println("Wachtwoord post-hash:" + hashWachtwoord(ww));
		System.out.println("*********************");
		
		
		Gebruiker g = ServiceImpl.zoekGebruiker(gb);
		if(hashWachtwoord(g.getWachtWoord()).equals(ww)) {
			return g;
		}
		return null;
	}
	
	public static String hashWachtwoord(String s) {
		/**
		MessageDigest m;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			return s;
		}
		m.reset();
		m.update(s.getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1,digest);
		String hashtext = bigInt.toString(16);
		
		while(hashtext.length() < 32 ){
		  hashtext = "0"+hashtext;
		}
		return hashtext;
		**/
		return s;
	}
}