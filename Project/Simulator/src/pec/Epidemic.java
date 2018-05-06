package pec;

import population.Individual;

import java.util.PriorityQueue;
import java.util.Random;

public class Epidemic extends Event {

	public void simulateEvent() {
		// TODO - implement Epidemic.simulateEvent

		PriorityQueue<Individual> pqCopy = new PriorityQueue<Individual>(getMe().getSimulator().getPopulation());
		int i = 0;
		Random rand = new Random();

		while (i < 4) { //Salva os 5 com maior conforto
			Individual obj = pqCopy.poll();
			i++;
		}

		while (!pqCopy.isEmpty()) { //Anda pela população verificando os sobreviventes
			Individual obj = pqCopy.poll();

			if (rand.nextDouble() <= getMe().getComfort()) { //Se ele não for sobreviver
				PriorityQueue<Event> evCpy = new PriorityQueue<Event>(getRef_pec().events);

				while (!pqCopy.isEmpty()) { //Remove os eventos do individuo que vai morrer
					Event obj2 = evCpy.poll();
					if (obj2.getMe() == obj) getRef_pec().events.remove(obj);
				}
				getMe().getSimulator().getPopulation().remove(obj); //Tira da população
			}

		}


        throw new UnsupportedOperationException();
    }


	public double generateTimestamp() {
		return 0;
	}

	public Epidemic(Individual me, PEC ref_pec) {
		super(me, 0, ref_pec);
	}
}