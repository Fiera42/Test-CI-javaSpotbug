package org.iut.tdd;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("Tests de la classe ListeNumeriques pour:")
public class ListesNumeriquesTest {

    @Order(1)
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @DisplayName("L'ajout d'un element neutre")
    @MethodSource("ajoutNeutreData")
    public void ajoutNeutre(
        List<Integer> x,
        List<Integer> y,
        List<Integer> res
    ) {
        ListesNumeriques liste = new ListesNumeriques();
        assertThat(liste.ajoute(x, y)).isEqualTo(res);
    }

    static Stream<Arguments> ajoutNeutreData() {
        return Stream.of(
            Arguments.of(Arrays.asList(1), Arrays.asList(0), Arrays.asList(1)),
            Arguments.of(
                Arrays.asList(1, 2, 3),
                Arrays.asList(0, 0, 0),
                Arrays.asList(1, 2, 3)
            ),
            Arguments.of(
                Arrays.asList(1, 2, 3, 4),
                Arrays.asList(0, 0, 0, 0),
                Arrays.asList(1, 2, 3, 4)
            ),
            Arguments.of(
                Arrays.asList(1, 2, 3, 4, 5),
                Arrays.asList(0, 0, 0, 0, 0),
                Arrays.asList(1, 2, 3, 4, 5)
            )
        );
    }

    @Order(2)
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @DisplayName("L'ajout de deux entiers")
    @MethodSource("ajoutData")
    public void ajoutSimple(
        List<Integer> x,
        List<Integer> y,
        List<Integer> res
    ) {
        ListesNumeriques liste = new ListesNumeriques();
        assertThat(liste.ajoute(x, y)).isEqualTo(res);
    }

    static Stream<Arguments> ajoutData() {
        return Stream.of(
            Arguments.of(Arrays.asList(1), Arrays.asList(1), Arrays.asList(2)),
            Arguments.of(
                Arrays.asList(1, 2, 3),
                Arrays.asList(3, 2, 1),
                Arrays.asList(4, 4, 4)
            ),
            Arguments.of(
                Arrays.asList(1, 2, 3, 4),
                Arrays.asList(4, 3, 2, 1),
                Arrays.asList(5, 5, 5, 5)
            ),
            Arguments.of(
                Arrays.asList(1, 2, 3, 4, 5),
                Arrays.asList(5, 4, 3, 2, 1),
                Arrays.asList(6, 6, 6, 6, 6)
            )
        );
    }

    @Order(3)
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @DisplayName("L'ajout de deux entiers de taille différente")
    @MethodSource("ajoutAsymetriqueData")
    public void ajoutAsymetrique(
        List<Integer> x,
        List<Integer> y,
        List<Integer> res
    ) {
        ListesNumeriques liste = new ListesNumeriques();
        assertThat(liste.ajoute(x, y)).isEqualTo(res);
    }

    static Stream<Arguments> ajoutAsymetriqueData() {
        return Stream.of(
            Arguments.of(
                Arrays.asList(1),
                Arrays.asList(0, 1),
                Arrays.asList(2)
            ),
            Arguments.of(
                Arrays.asList(1, 2),
                Arrays.asList(3, 2, 1),
                Arrays.asList(3, 3, 3)
            ),
            Arguments.of(
                Arrays.asList(1, 2, 3),
                Arrays.asList(2, 1),
                Arrays.asList(1, 4, 4)
            ),
            Arguments.of(
                Arrays.asList(1, 2),
                Arrays.asList(4, 3, 2, 1),
                Arrays.asList(4, 3, 3, 3)
            ),
            Arguments.of(
                Arrays.asList(1, 2, 3, 4, 5),
                Arrays.asList(3, 2),
                Arrays.asList(1, 2, 3, 7, 7)
            )
        );
    }

    @Order(4)
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @DisplayName("L'ajout d'un element vide")
    @MethodSource("ajoutVideData")
    public void ajoutVide(List<Integer> x, List<Integer> y, List<Integer> res) {
        ListesNumeriques liste = new ListesNumeriques();
        assertThat(liste.ajoute(x, y)).isEqualTo(res);
    }

    static Stream<Arguments> ajoutVideData() {
        return Stream.of(
            Arguments.of(Arrays.asList(1), Arrays.asList(), Arrays.asList(1)),
            Arguments.of(
                Arrays.asList(),
                Arrays.asList(3, 2, 1),
                Arrays.asList(3, 2, 1)
            ),
            Arguments.of(
                Arrays.asList(1, 2, 3, 4),
                Arrays.asList(),
                Arrays.asList(1, 2, 3, 4)
            ),
            Arguments.of(
                Arrays.asList(),
                Arrays.asList(5, 4, 3, 2, 1),
                Arrays.asList(5, 4, 3, 2, 1)
            )
        );
    }

    @Order(5)
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @DisplayName("L'ajout d'un element avec wrapping")
    @MethodSource("ajoutWrappingData")
    public void ajoutWrapping(
        List<Integer> x,
        List<Integer> y,
        List<Integer> res
    ) {
        ListesNumeriques liste = new ListesNumeriques();
        assertThat(liste.ajoute(x, y)).isEqualTo(res);
    }

    static Stream<Arguments> ajoutWrappingData() {
        return Stream.of(
            Arguments.of(
                Arrays.asList(1, 9, 9),
                Arrays.asList(3, 0, 1),
                Arrays.asList(5, 0, 0)
            ),
            Arguments.of(
                Arrays.asList(1, 2, 8, 4),
                Arrays.asList(4, 3, 5, 1),
                Arrays.asList(5, 6, 3, 5)
            ),
            Arguments.of(
                Arrays.asList(0, 6, 4, 2, 1),
                Arrays.asList(0, 9, 9, 9, 9),
                Arrays.asList(1, 6, 4, 2, 0)
            )
        );
    }

    @Order(6)
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @DisplayName("L'ajout d'un element avec overflow")
    @MethodSource("ajoutOverflowData")
    public void ajoutOverflow(
        List<Integer> x,
        List<Integer> y,
        List<Integer> res
    ) {
        ListesNumeriques liste = new ListesNumeriques();
        assertThat(liste.ajoute(x, y)).isEqualTo(res);
    }

    static Stream<Arguments> ajoutOverflowData() {
        return Stream.of(
            Arguments.of(
                Arrays.asList(1),
                Arrays.asList(9),
                Arrays.asList(1, 0)
            ),
            Arguments.of(
                Arrays.asList(1, 9, 9),
                Arrays.asList(8, 0, 1),
                Arrays.asList(1, 0, 0, 0)
            ),
            Arguments.of(
                Arrays.asList(8, 2, 8, 4),
                Arrays.asList(8, 3, 5, 1),
                Arrays.asList(1, 6, 6, 3, 5)
            ),
            Arguments.of(
                Arrays.asList(6, 4, 2, 1),
                Arrays.asList(9, 9, 9, 9),
                Arrays.asList(1, 6, 4, 2, 0)
            )
        );
    }

    @Order(7)
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @DisplayName("L'ajout d'un element negatif")
    @MethodSource("ajoutNegatifData")
    public void ajoutNegatif(
        List<Integer> x,
        List<Integer> y,
        List<Integer> res
    ) {
        ListesNumeriques liste = new ListesNumeriques();
        assertThat(liste.ajoute(x, y)).isEqualTo(res);
    }

    static Stream<Arguments> ajoutNegatifData() {
        return Stream.of(
            Arguments.of(
                Arrays.asList(-1, 2, 3),
                Arrays.asList(-3, 2, 1),
                Arrays.asList(-4, 4, 4)
            ),
            Arguments.of(
                Arrays.asList(-1, 2, 3),
                Arrays.asList(3, 2, 1),
                Arrays.asList(1, 9, 8)
            ),
            Arguments.of(
                Arrays.asList(1, 2, 3),
                Arrays.asList(-3, 2, 1),
                Arrays.asList(-1, 9, 8)
            )
        );
    }

    @Order(8)
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @DisplayName("L'ajout d'un element negatif avec wrap")
    @MethodSource("ajoutNegatifWrapData")
    public void ajoutNegatifWrap(
        List<Integer> x,
        List<Integer> y,
        List<Integer> res
    ) {
        ListesNumeriques liste = new ListesNumeriques();
        assertThat(liste.ajoute(x, y)).isEqualTo(res);
    }

    static Stream<Arguments> ajoutNegatifWrapData() {
        return Stream.of(
            Arguments.of(
                Arrays.asList(-1, 9, 9, 9),
                Arrays.asList(-1),
                Arrays.asList(-2, 0, 0, 0)
            ),
            Arguments.of(
                Arrays.asList(2, 0, 0, 0),
                Arrays.asList(-1),
                Arrays.asList(1, 9, 9, 9)
            ),
            Arguments.of(
                Arrays.asList(-2, 0, 0, 0),
                Arrays.asList(1),
                Arrays.asList(-1, 9, 9, 9)
            )
        );
    }

    @Order(9)
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @DisplayName("L'ajout d'un element negatif avec overflow")
    @MethodSource("ajoutNegatifOverflowData")
    public void ajoutNegatifOverflow(
        List<Integer> x,
        List<Integer> y,
        List<Integer> res
    ) {
        ListesNumeriques liste = new ListesNumeriques();
        assertThat(liste.ajoute(x, y)).isEqualTo(res);
    }

    static Stream<Arguments> ajoutNegatifOverflowData() {
        return Stream.of(
            Arguments.of(
                Arrays.asList(-9, 4, 5, 0),
                Arrays.asList(-8, 0, 0),
                Arrays.asList(-1, 0, 2, 5, 0)
            ),
            Arguments.of(
                Arrays.asList(1, 0, 0, 0),
                Arrays.asList(-1, 0),
                Arrays.asList(9, 9, 0)
            ),
            Arguments.of(
                Arrays.asList(-1, 0, 0, 0),
                Arrays.asList(1, 0),
                Arrays.asList(-9, 9, 0)
            )
        );
    }

    @Order(10)
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @DisplayName("L'ajout d'un element pour faire basculer le signe")
    @MethodSource("ajoutBasculeData")
    public void ajoutBascule(
        List<Integer> x,
        List<Integer> y,
        List<Integer> res
    ) {
        ListesNumeriques liste = new ListesNumeriques();
        assertThat(liste.ajoute(x, y)).isEqualTo(res);
    }

    static Stream<Arguments> ajoutBasculeData() {
        return Stream.of(
            Arguments.of(
                Arrays.asList(5, 5, 5, 5),
                Arrays.asList(-7, 7, 7, 7),
                Arrays.asList(-2, 2, 2, 2)
            ),
            Arguments.of(
                Arrays.asList(-5, 5, 5, 5),
                Arrays.asList(7, 7, 7, 7),
                Arrays.asList(2, 2, 2, 2)
            )
        );
    }

    @Order(11)
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @DisplayName("L'ajout d'un element pour faire basculer le signe avec wrap")
    @MethodSource("ajoutBasculeWrapData")
    public void ajoutBasculeWrap(
        List<Integer> x,
        List<Integer> y,
        List<Integer> res
    ) {
        ListesNumeriques liste = new ListesNumeriques();
        assertThat(liste.ajoute(x, y)).isEqualTo(res);
    }

    static Stream<Arguments> ajoutBasculeWrapData() {
        return Stream.of(
            Arguments.of(
                Arrays.asList(5, 5, 5, 5),
                Arrays.asList(-7, 7, 2, 7),
                Arrays.asList(-2, 1, 7, 2)
            ),
            Arguments.of(
                Arrays.asList(-5, 5, 5, 5),
                Arrays.asList(7, 7, 2, 7),
                Arrays.asList(2, 1, 7, 2)
            )
        );
    }

    @Order(12)
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @DisplayName(
        "L'ajout d'un element pour faire basculer le signe avec overflow"
    )
    @MethodSource("ajoutBasculeOverflowData")
    public void ajoutBasculeOverflow(
        List<Integer> x,
        List<Integer> y,
        List<Integer> res
    ) {
        ListesNumeriques liste = new ListesNumeriques();
        assertThat(liste.ajoute(x, y)).isEqualTo(res);
    }

    static Stream<Arguments> ajoutBasculeOverflowData() {
        return Stream.of(
            Arguments.of(
                Arrays.asList(7, 5, 5, 5),
                Arrays.asList(-7, 7, 2, 7),
                Arrays.asList(-1, 7, 2)
            ),
            Arguments.of(
                Arrays.asList(-7, 5, 5, 5),
                Arrays.asList(7, 7, 2, 7),
                Arrays.asList(1, 7, 2)
            )
        );
    }

    @Order(13)
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @DisplayName("Le nombre de zéros")
    @MethodSource("ajoutZeroData")
    public void ajoutZero(List<Integer> x, List<Integer> y, List<Integer> res) {
        ListesNumeriques liste = new ListesNumeriques();
        assertThat(liste.ajoute(x, y)).isEqualTo(res);
    }

    static Stream<Arguments> ajoutZeroData() {
        return Stream.of(
            Arguments.of(
                Arrays.asList(0, 0, 1, 2, 3),
                Arrays.asList(3, 2, 1),
                Arrays.asList(4, 4, 4)
            ),
            Arguments.of(
                Arrays.asList(1, 2, 3),
                Arrays.asList(0, 0, 3, 2, 1),
                Arrays.asList(4, 4, 4)
            ),
            Arguments.of(
                Arrays.asList(0, 0, 1, 2, 3),
                Arrays.asList(0, 0, 3, 2, 1),
                Arrays.asList(4, 4, 4)
            ),
            Arguments.of(
                Arrays.asList(0, 0, -1, 2, 3),
                Arrays.asList(0, 0, 3, 2, 1),
                Arrays.asList(1, 9, 8)
            ),
            Arguments.of(
                Arrays.asList(0, 0, 1, 2, 3),
                Arrays.asList(0, 0, -3, 2, 1),
                Arrays.asList(-1, 9, 8)
            ),
            Arguments.of(
                Arrays.asList(0, 0, -1, 2, 3),
                Arrays.asList(0, 0, -3, 2, 1),
                Arrays.asList(-4, 4, 4)
            ),
            Arguments.of(
                Arrays.asList(1, 2, 3),
                Arrays.asList(-1, 2, 3),
                Arrays.asList(0)
            ),
            Arguments.of(
                Arrays.asList(-1, 2, 3),
                Arrays.asList(1, 2, 3),
                Arrays.asList(0)
            ),
            Arguments.of(
                Arrays.asList(0, 0, 0),
                Arrays.asList(0, 0, 0),
                Arrays.asList(0)
            ),
            Arguments.of(null, Arrays.asList(0, 0, 0), Arrays.asList(0)),
            Arguments.of(Arrays.asList(0, 0, 0), null, Arrays.asList(0)),
            Arguments.of(null, null, Arrays.asList(0)),
            Arguments.of(
                Arrays.asList(),
                Arrays.asList(0, 0, 0),
                Arrays.asList(0)
            ),
            Arguments.of(
                Arrays.asList(0, 0, 0),
                Arrays.asList(),
                Arrays.asList(0)
            ),
            Arguments.of(Arrays.asList(), Arrays.asList(), Arrays.asList(0))
        );
    }

    @Order(14)
    @ParameterizedTest(name = "{0} + {1}")
    @DisplayName("L'ajout d'un element negatif interdit")
    @MethodSource("ajoutNegatifIllegalData")
    public void ajoutNegatifIllegal(
        List<Integer> x,
        List<Integer> y,
        List<Integer> res
    ) {
        ListesNumeriques liste = new ListesNumeriques();
        Assertions
            .assertThatThrownBy(() -> liste.ajoute(x, y))
            .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> ajoutNegatifIllegalData() {
        return Stream.of(
            Arguments.of(
                Arrays.asList(1, -2, 3),
                Arrays.asList(3, 2, 1),
                Arrays.asList(-4, 4, 4)
            ),
            Arguments.of(
                Arrays.asList(1, 2, 3),
                Arrays.asList(3, 2, -1),
                Arrays.asList(1, 9, 8)
            ),
            Arguments.of(
                Arrays.asList(-1, 2, -3),
                Arrays.asList(3, 2, 1),
                Arrays.asList(-2, 0, 2)
            ),
            Arguments.of(
                Arrays.asList(1, 2, 3),
                Arrays.asList(-3, -2, 1),
                Arrays.asList(-2, 0, 2)
            )
        );
    }

    @Order(15)
    @ParameterizedTest(name = "{0} + {1}")
    @DisplayName("L'ajout d'un element trop grand")
    @MethodSource("ajoutBigData")
    public void ajoutBig(List<Integer> x, List<Integer> y, List<Integer> res) {
        ListesNumeriques liste = new ListesNumeriques();
        Assertions
            .assertThatThrownBy(() -> liste.ajoute(x, y))
            .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> ajoutBigData() {
        return Stream.of(
            Arguments.of(
                Arrays.asList(1, 20, 3),
                Arrays.asList(3, 2, 1),
                Arrays.asList(-4, 4, 4)
            ),
            Arguments.of(
                Arrays.asList(1, 2, 3),
                Arrays.asList(3, 2, 11),
                Arrays.asList(1, 9, 8)
            ),
            Arguments.of(
                Arrays.asList(10, 2, 3),
                Arrays.asList(3, 2, 1),
                Arrays.asList(-2, 0, 2)
            ),
            Arguments.of(
                Arrays.asList(-10, 2, 3),
                Arrays.asList(3, 2, 1),
                Arrays.asList(-2, 0, 2)
            ),
            Arguments.of(
                Arrays.asList(8, 2, 3),
                Arrays.asList(-10, 2, 1),
                Arrays.asList(-2, 0, 2)
            )
        );
    }

    @Order(16)
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @DisplayName("L'ajout d'un element null")
    @MethodSource("ajoutNullData")
    public void ajoutNull(List<Integer> x, List<Integer> y, List<Integer> res) {
        ListesNumeriques liste = new ListesNumeriques();
        assertThat(liste.ajoute(x, y)).isEqualTo(res);
    }

    static Stream<Arguments> ajoutNullData() {
        return Stream.of(
            Arguments.of(null, Arrays.asList(3, 2, 1), Arrays.asList(3, 2, 1)),
            Arguments.of(Arrays.asList(3, 2, 1), null, Arrays.asList(3, 2, 1)),
            Arguments.of(null, null, Arrays.asList(0))
        );
    }

    @Order(17)
    @ParameterizedTest(name = "{0} + {1}")
    @DisplayName("L'ajout d'un element contenant des null")
    @MethodSource("ajoutSomeNullData")
    public void ajoutSomeNull(
        List<Integer> x,
        List<Integer> y,
        List<Integer> res
    ) {
        ListesNumeriques liste = new ListesNumeriques();
        Assertions
            .assertThatThrownBy(() -> liste.ajoute(x, y))
            .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> ajoutSomeNullData() {
        return Stream.of(
            Arguments.of(
                Arrays.asList(1),
                Arrays.asList((Integer) null),
                Arrays.asList(2)
            ),
            Arguments.of(
                Arrays.asList(1, null, 3),
                Arrays.asList(3, 2, 1),
                Arrays.asList(4, 4, 4)
            ),
            Arguments.of(
                Arrays.asList(1, 2, 3, null),
                Arrays.asList(null, 3, 2, 1),
                Arrays.asList(5, 5, 5, 5)
            ),
            Arguments.of(
                Arrays.asList(1, null, 3, 4, 5),
                Arrays.asList(5, 4, 3, 2, null),
                Arrays.asList(6, 6, 6, 6, 6)
            )
        );
    }

    @Order(18)
    @ParameterizedTest(name = "{0} + {1}")
    @DisplayName("L'ajout d'un element null et d'un element illegal")
    @MethodSource("ajoutNullIllegalData")
    public void ajoutNullIllegal(
        List<Integer> x,
        List<Integer> y,
        List<Integer> res
    ) {
        ListesNumeriques liste = new ListesNumeriques();
        Assertions
            .assertThatThrownBy(() -> liste.ajoute(x, y))
            .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> ajoutNullIllegalData() {
        return Stream.of(
            Arguments.of(null, Arrays.asList(-1, -1), Arrays.asList(2)),
            Arguments.of(null, Arrays.asList(20, -20), Arrays.asList(2)),
            Arguments.of(null, Arrays.asList(null, null), Arrays.asList(2)),
            Arguments.of(Arrays.asList(null, null), null, Arrays.asList(2)),
            Arguments.of(Arrays.asList(-1, -1), null, Arrays.asList(2)),
            Arguments.of(Arrays.asList(20, -10), null, Arrays.asList(2))
        );
    }

    @Order(19)
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @DisplayName("L'ajout d'un element impossible a representer")
    @MethodSource("ajoutMASSIVEData")
    public void ajoutMASSIVE(
        List<Integer> x,
        List<Integer> y,
        List<Integer> res
    ) {
        ListesNumeriques liste = new ListesNumeriques();
        assertThat(liste.ajoute(x, y)).isEqualTo(res);
    }

    static Stream<Arguments> ajoutMASSIVEData() {
        return Stream.of(
            Arguments.of(
                Arrays.asList(
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1
                ),
                Arrays.asList(
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1
                ),
                Arrays.asList(
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2
                )
            ),
            Arguments.of(
                Arrays.asList(
                    -1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1
                ),
                Arrays.asList(
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2
                ),
                Arrays.asList(
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1
                )
            )
        );
    }

    @Order(20)
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @DisplayName("L'ajout à partir de liste immutables")
    @MethodSource("ajoutImmutableData")
    public void ajoutImmutable(
        List<Integer> x,
        List<Integer> y,
        List<Integer> res
    ) {
        ListesNumeriques liste = new ListesNumeriques();
        assertThat(liste.ajoute(x, y)).isEqualTo(res);
    }

    static Stream<Arguments> ajoutImmutableData() {
        return Stream.of(
            Arguments.of(List.of(1), List.of(1), List.of(2)),
            Arguments.of(
                List.of(1, 2, 3),
                List.of(-3, 2, 1),
                List.of(-1, 9, 8)
            ),
            Arguments.of(
                List.of(-1, 2, 3, 4),
                List.of(4, 3, 2, 1),
                List.of(3, 0, 8, 7)
            ),
            Arguments.of(
                List.of(-1, 2, 3, 4, 5),
                List.of(-5, 4, 3, 2, 1),
                List.of(-6, 6, 6, 6, 6)
            )
        );
    }

    @Order(21)
    @ParameterizedTest(name = "{0} + {1}")
    @DisplayName("Que le resultat est une nouvelle liste")
    @MethodSource("ajoutReferenceData")
    public void ajoutReference(List<Integer> x, List<Integer> y) {
        ListesNumeriques liste = new ListesNumeriques();

        assertThat(liste.ajoute(x, y) == x).isEqualTo(false);
        assertThat(liste.ajoute(x, y) == y).isEqualTo(false);
    }

    static Stream<Arguments> ajoutReferenceData() {
        return Stream.of(
            Arguments.of(Arrays.asList(0), Arrays.asList(1, 2, 3)),
            Arguments.of(Arrays.asList(1, 2, 3), Arrays.asList(0)),
            Arguments.of(Arrays.asList(1, 2, 3), Arrays.asList(1, 2, 3)),
            Arguments.of(Arrays.asList(-1, 2, 3), Arrays.asList(1, 2, 3)),
            Arguments.of(Arrays.asList(1, 2, 3), Arrays.asList(-1, 2, 3)),
            Arguments.of(Arrays.asList(-1, 2, 3), Arrays.asList(-1, 2, 3)),
            Arguments.of(Arrays.asList(), Arrays.asList(1, 2, 3)),
            Arguments.of(Arrays.asList(1, 2, 3), Arrays.asList()),
            Arguments.of(null, Arrays.asList(1, 2, 3)),
            Arguments.of(Arrays.asList(1, 2, 3), null),
            Arguments.of(Arrays.asList(), Arrays.asList(-1, 2, 3)),
            Arguments.of(Arrays.asList(-1, 2, 3), Arrays.asList()),
            Arguments.of(null, Arrays.asList(-1, 2, 3)),
            Arguments.of(Arrays.asList(-1, 2, 3), null)
        );
    }

    @Order(22)
    @ParameterizedTest(name = "{0} compareTo {1} = {2}")
    @DisplayName("La comparaison de deux listes")
    @MethodSource("comparaisonData")
    public void comparaison(List<Integer> x, List<Integer> y, int res) {
        ListesNumeriques liste = new ListesNumeriques();
        assertThat(liste.compareListToList(x, y)).isEqualTo(res);
    }

    static Stream<Arguments> comparaisonData() {
        return Stream.of(
            Arguments.of(List.of(1, 2, 3), List.of(4, 5, 6), -1),
            Arguments.of(List.of(4, 5, 6), List.of(4, 5, 6), 0),
            Arguments.of(List.of(4, 5, 6), List.of(1, 2, 3), 1)
        );
    }

    @Order(23)
    @ParameterizedTest(name = "{0} compareTo {1} = {2}")
    @DisplayName("La comparaison de deux listes negatives")
    @MethodSource("comparaisonNegativeData")
    public void comparaisonNegative(List<Integer> x, List<Integer> y, int res) {
        ListesNumeriques liste = new ListesNumeriques();
        assertThat(liste.compareListToList(x, y)).isEqualTo(res);
    }

    static Stream<Arguments> comparaisonNegativeData() {
        return Stream.of(
            Arguments.of(List.of(-1, 2, 3), List.of(-4, 5, 6), 1),
            Arguments.of(List.of(-4, 5, 6), List.of(-4, 5, 6), 0),
            Arguments.of(List.of(-4, 5, 6), List.of(-1, 2, 3), -1)
        );
    }

    @Order(24)
    @ParameterizedTest(name = "{0} compareTo {1} = {2}")
    @DisplayName("La comparaison de deux listes de tailles differentes")
    @MethodSource("comparaisonTailleData")
    public void comparaisonTaille(List<Integer> x, List<Integer> y, int res) {
        ListesNumeriques liste = new ListesNumeriques();
        assertThat(liste.compareListToList(x, y)).isEqualTo(res);
    }

    static Stream<Arguments> comparaisonTailleData() {
        return Stream.of(
            Arguments.of(List.of(1, 2, 3, 4), List.of(4, 5, 6), 1),
            Arguments.of(List.of(1, 2, 3), List.of(4, 5, 6, 7), -1),
            Arguments.of(List.of(-1, 2, 3, 4), List.of(-4, 5, 6), -1),
            Arguments.of(List.of(-1, 2, 3), List.of(-4, 5, 6, 7), 1)
        );
    }

    @Order(25)
    @ParameterizedTest(name = "{0} compareTo {1} = {2}")
    @DisplayName("La comparaison de deux listes de signe differentes")
    @MethodSource("comparaisonSigneData")
    public void comparaisonSigne(List<Integer> x, List<Integer> y, int res) {
        ListesNumeriques liste = new ListesNumeriques();
        assertThat(liste.compareListToList(x, y)).isEqualTo(res);
    }

    static Stream<Arguments> comparaisonSigneData() {
        return Stream.of(
            Arguments.of(List.of(1, 2, 3), List.of(-4, 5, 6), 1),
            Arguments.of(List.of(-4, 5, 6), List.of(1, 2, 3), -1)
        );
    }

    @Order(26)
    @ParameterizedTest(name = "{0} compareTo {1} = {2}")
    @DisplayName("La comparaison de deux listes null ou vide")
    @MethodSource("comparaisonEmptyNullData")
    public void comparaisonEmptyNull(
        List<Integer> x,
        List<Integer> y,
        int res
    ) {
        ListesNumeriques liste = new ListesNumeriques();
        assertThat(liste.compareListToList(x, y)).isEqualTo(res);
    }

    static Stream<Arguments> comparaisonEmptyNullData() {
        return Stream.of(
            Arguments.of(Arrays.asList(), Arrays.asList(1, 0, 0), -1),
            Arguments.of(Arrays.asList(), Arrays.asList(), 0),
            Arguments.of(Arrays.asList(1, 0, 0), Arrays.asList(), 1),
            Arguments.of(null, Arrays.asList(1, 0, 0), -1),
            Arguments.of(null, null, 0),
            Arguments.of(Arrays.asList(1, 0, 0), null, 1),
            Arguments.of(Arrays.asList(), null, 0),
            Arguments.of(null, Arrays.asList(), 0)
        );
    }

    @Order(27)
    @ParameterizedTest(name = "{0} compareTo {1} = {2}")
    @DisplayName("La comparaison de deux listes illegal")
    @MethodSource("comparaisonIllegalData")
    public void comparaisonIllegal(List<Integer> x, List<Integer> y, int res) {
        ListesNumeriques liste = new ListesNumeriques();
        Assertions
            .assertThatThrownBy(() -> liste.compareListToList(x, y))
            .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> comparaisonIllegalData() {
        return Stream.of(
            Arguments.of(Arrays.asList(1, -2, 3), Arrays.asList(3, 2, 1), 0),
            Arguments.of(Arrays.asList(1, 2, 3), Arrays.asList(3, 2, -1), 0),
            Arguments.of(Arrays.asList(-1, 2, -3), Arrays.asList(3, 2, 1), 0),
            Arguments.of(Arrays.asList(1, 2, 3), Arrays.asList(-3, -2, 1), 0),
            Arguments.of(Arrays.asList(1, 20, 3), Arrays.asList(3, 2, 1), 0),
            Arguments.of(Arrays.asList(1, 2, 3), Arrays.asList(3, 2, 11), 0),
            Arguments.of(Arrays.asList(10, 2, 3), Arrays.asList(3, 2, 1), 0),
            Arguments.of(Arrays.asList(-10, 2, 3), Arrays.asList(3, 2, 1), 0),
            Arguments.of(Arrays.asList(8, 2, 3), Arrays.asList(-10, 2, 1), 0),
            Arguments.of(Arrays.asList(1), Arrays.asList((Integer) null), 0),
            Arguments.of(Arrays.asList(1, null, 3), Arrays.asList(3, 2, 1), 0),
            Arguments.of(
                Arrays.asList(1, 2, 3, null),
                Arrays.asList(null, 3, 2, 1),
                0
            ),
            Arguments.of(
                Arrays.asList(1, null, 3, 4, 5),
                Arrays.asList(5, 4, 3, 2, null),
                0
            )
        );
    }
}
