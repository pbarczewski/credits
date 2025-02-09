package pl.pbarczewski.util;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

// Klasa pomocnicza wykorzystywana do nadania unikatowego numeru ID 
// dla każdej z klas reprezentujących encje wykorzystywane w aplikacji.
public class IdGenerator {
	private static final Set<Integer> creditIdSet = new HashSet<>();
	private static int creditId;
	
	// Metoda statyczna realizująca główne założenie klasy.
	// Wykorzystuje "ThreadLocalRandom" do wygenerowania liczby całkowitej z zakresu od 1 do 1000000.
	// Zwraca wartość typu "int".
	public static int generateId() throws IllegalArgumentException, IllegalAccessException {
		boolean isAvailable = true;
		while(isAvailable) {
			creditId = ThreadLocalRandom.current().nextInt(1, 1000000);
			if(!creditIdSet.contains(creditId)) {
				creditIdSet.add(creditId);
				isAvailable = false;
			}
		}
		return creditId;
	}
}
