package org.iut.tdd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * La classe ListesNumeriques permet d'ajouter 2 entiers (positifs ou négatifs) représentés en
 * utilisant des listes de chiffres. On ne passera pas par un type <code>Long</code> ou
 * <code>BigInteger</code>.
 */
public class ListesNumeriques {

    /**
     * <p>La méthode considère 2 entiers qui sont représentés en utilisant des listes de
     * chiffres, respectivement <code>nb1</code> et <code>nb2</code>. Cela permet de pouvoir
     * manipuler des très grands nombres sans utiliser un type borné tel que <code>Long</code>
     * par exemple.
     * La méthode ajoute ces 2 nombres et renvoie le résultat comme une liste de chiffres.
     * </p>
     * <p>Par exemple, si on veut ajouter les nombres 142 et 13, on doit créer une liste
     * (nb1) avec trois éléments [1,4,2] et une liste (nb2) avec deux éléments [1,3].
     * Comme 142+13 = 155, le programme doit donc produire une liste avec trois éléments [1,5,5]
     * </p>
     * [1,4,2] + [1,3] = [1,5,5]
     * <p>
     * Chaque élément des listes nb1 et nb2 doit être compris entre 0 et 9, excepté le premier
     * élément de chaque liste qui peut être compris entre -9 et 0 pour pouvoir représenter un nombre
     * négatif.
     * Une exception <code>IllegalArgumentException</code> est levée si cette condition préalable n'est
     * pas remplie.
     * </p>
     *
     * @param nb1_original liste qui contient le premier entier. Si <code>nb1</code> est une liste vide, cela
     *                     correspond à 0. Si <code>nb1</code> est <code>null</code>, le résultat est une liste vide
     * @param nb2_original liste qui contient le deuxième entier. Si <code>nb2</code> est une liste vide, cela
     *                     correspond à 0. Si <code>nb2</code> est <code>null</code>, le résultat est une liste vide
     * @return la somme de <code>nb1</code> et <code>nb2</code> représentée comme une liste de chiffres
     */
    public List<Integer> ajoute(List<Integer> nb1_original, List<Integer> nb2_original) {
        List<Integer> nb1 = trimList(nb1_original);
        List<Integer> nb2 = trimList(nb2_original);

        if (nb1 != null) {
            isListValid(nb1, "Argument 1");
        }
        if (nb2 != null) {
            isListValid(nb2, "Argument 2");
        }

        if ((nb1 == null || nb1.isEmpty()) && (nb2 == null || nb2.isEmpty())) {
            return trimList(new ArrayList<>());
        }
        if ((nb1 == null || nb1.isEmpty()) && !(nb2 == null || nb2.isEmpty())) {
            return trimList(new ArrayList<>(nb2));
        }
        if (!(nb1 == null || nb1.isEmpty()) && (nb2 == null || nb2.isEmpty())) {
            return trimList(new ArrayList<>(nb1));
        }
        boolean isSubstraction = (nb1.get(0) < 0) != (nb2.get(0) < 0);

        List<Integer> res;
        if (isSubstraction) {
            res = (nb1.get(0) > 0) ? roughSubtract(nb1, nb2) : roughSubtract(nb2, nb1);
        } else {
            res = roughAdd(nb1, nb2);
        }

        return res;
    }

    // Assume two list ready to be summed and of same sign
    // UTILS OF "ajouter"
    private List<Integer> roughAdd(List<Integer> nb1, List<Integer> nb2) {
        ArrayList<Integer> res = new ArrayList<>();
        List<Integer> reversed1 = new ArrayList<>(nb1);
        List<Integer> reversed2 = new ArrayList<>(nb2);
        Collections.reverse(reversed1);
        Collections.reverse(reversed2);

        int i = 0;
        int remainder = 0;
        while (i < reversed1.size() || i < reversed2.size()) {
            int val1 = (i < reversed1.size()) ? reversed1.get(i) : 0;
            int val2 = (i < reversed2.size()) ? reversed2.get(i) : 0;
            int total = Math.abs(val1) + Math.abs(val2) + remainder;
            if (total > 9) {
                remainder = 1;
                total = Integer.max(0, total - 10);
            } else {
                remainder = 0;
            }
            res.add(total);
            i++;
        }
        res.add(remainder);

        Collections.reverse(res);
        res = (ArrayList<Integer>) trimList(res);
        if (nb1.get(0) < 0) res.set(0, -res.get(0));

        return res;
    }

    // Assume two list ready to be subtracted, ignore input sign (always do nb1 - nb2)
    // UTILS OF "ajouter"
    private List<Integer> roughSubtract(List<Integer> nb1, List<Integer> nb2) {
        // Make sure there is no sign interference
        List<Integer> reversed1 = new ArrayList<>(nb1);
        List<Integer> reversed2 = new ArrayList<>(nb2);
        reversed1.set(0, Math.abs(reversed1.get(0)));
        reversed2.set(0, Math.abs(reversed2.get(0)));

        boolean resIsNegative = false;
        if (compareListToList(reversed1, reversed2) < 0) { // Biggest number always on top of the subtraction
            List<Integer> temp = reversed1;
            reversed1 = reversed2;
            reversed2 = temp;
            resIsNegative = true;
        }
        Collections.reverse(reversed1);
        Collections.reverse(reversed2);

        ArrayList<Integer> res = new ArrayList<>();
        int i = 0;
        int remainder = 0;
        while (i < reversed1.size() || i < reversed2.size()) {
            int val1 = (i < reversed1.size()) ? reversed1.get(i) : 0;
            int val2 = (i < reversed2.size()) ? reversed2.get(i) : 0;
            val2 += remainder;
            int total = Math.abs(val1) - Math.abs(val2);

            if (total < 0) {
                remainder = 1;
                total += 10;
            } else {
                remainder = 0;
            }

            res.add(total);
            i++;
        }

        Collections.reverse(res);
        res = (ArrayList<Integer>) trimList(res);
        if (resIsNegative) res.set(0, -res.get(0));

        return res;
    }

    private List<Integer> trimList(List<Integer> list) {
        if (list == null) return null;

        List<Integer> res = new ArrayList<>(list);

        while (!res.isEmpty() && res.get(0) != null && res.get(0) == 0) {
            res.remove(0);
        }

        if (res.isEmpty()) {
            res.add(0);
        }

        return res;
    }

    private void isListValid(List<Integer> list, String header)
            throws IllegalArgumentException {
        for (int i = 0; i < list.size(); i++) {
            Integer value = list.get(i);
            if (value == null) {
                throw new IllegalArgumentException(header + ": Argument at position " + i + " is null");
            }

            if (i != 0) {
                if (value < 0) {
                    throw new IllegalArgumentException(header + ": Argument at position " + i + " (" + value + ") is negative");
                }
            }
            if (Math.abs(value) > 9) {
                throw new IllegalArgumentException(header + ": Argument at position " + i + " (" + value + ") is greater than 9");
            }
        }
    }

    public int compareListToList(List<Integer> nb1, List<Integer> nb2) {
        if (nb1 != null) {
            isListValid(nb1, "Argument 1");
        }
        if (nb2 != null) {
            isListValid(nb2, "Argument 2");
        }

        // not null
        if (
                (nb1 == null || nb1.isEmpty()) && (nb2 == null || nb2.isEmpty())
        ) return 0;
        if (
                !(nb1 == null || nb1.isEmpty()) && (nb2 == null || nb2.isEmpty())
        ) return 1;
        if (
                (nb1 == null || nb1.isEmpty()) && !(nb2 == null || nb2.isEmpty())
        ) return -1;

        // not null same sign
        if (nb1.get(0) < 0 && nb2.get(0) > 0) return -1;
        if (nb1.get(0) > 0 && nb2.get(0) < 0) return 1;

        int sign = (nb1.get(0) > 0) ? 1 : -1;

        // not null same sign same length
        if (sign > 0) {
            if (nb1.size() > nb2.size()) return 1;
            if (nb1.size() < nb2.size()) return -1;
        } else {
            if (nb1.size() > nb2.size()) return -1;
            if (nb1.size() < nb2.size()) return 1;
        }

        // not null same sign same length
        // -> can safely compare the numbers
        for (int i = 0; i < nb1.size(); i++) {
            int val1 = Math.abs(nb1.get(i));
            int val2 = Math.abs(nb2.get(i));

            if (sign > 0) {
                if (val1 > val2) return 1;
                if (val1 < val2) return -1;
            } else {
                if (val1 > val2) return -1;
                if (val1 < val2) return 1;
            }
        }

        return 0;
    }
}
