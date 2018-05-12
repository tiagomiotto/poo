package pec;

import population.Individual;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * Epidemic.java - A class extending the event class in order to determine the reproduction behaviour of each
 * individual.
 * It doesn't need to have a timestamp generator since it's always the next event to be simulated when
 * it's added to the PEC.
 * It contains the individual it is related to, the time in which it should be simulated, and a reference to
 * the PEC simulating it.
 *
 * @author Tiago Miotto
 * @version 1.0
 * @see Event
 */
public class Epidemic extends Event {

    /**
     * Method defining the individuals epidemic behaviour. Which saves the 5 individuals
     * with the biggest comfort, and then observe a random value to determine if each of the
     * other ones survive, if the value is bigger than the individual's comfort, it dies.
     */
    public void simulateEvent() {

        PriorityQueue<Individual> pqCopy = new PriorityQueue<Individual>(getMe().getSimulator().getPopulation());
        int i = 0;
        Random rand = new Random();
        int counter = getMe().getSimulator().getPopulation().size();
        while (i < 4) { //Salva os 5 com maior conforto
            Individual obj = pqCopy.poll();
            i++;
        }

        while (!pqCopy.isEmpty()) { //Anda pela população verificando os sobreviventes
            Individual obj = pqCopy.poll();
            counter--;
            if ((Double.compare(rand.nextDouble(), obj.getComfort())) == 1) { //Se ele não for sobreviver
                PriorityQueue<Event> evCpy = new PriorityQueue<Event>(getRef_pec().events);
                counter++;
                while (!evCpy.isEmpty()) { //Remove os eventos do individuo que vai morrer
                    Event obj2 = evCpy.poll();
                    if (obj2.getMe() == obj) getRef_pec().events.remove(obj2);
                }
                getMe().getSimulator().getPopulation().remove(obj); //Tira da população
            }

        }


    }

    /**
     * Constructor
     */
    Epidemic(Individual me, PEC ref_pec) {
        super(me, 0, ref_pec);
    }
}