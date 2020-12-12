@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson5.task1

import java.lang.Integer.max

// Урок 5: ассоциативные массивы и множества
// Максимальное количество баллов = 14
// Рекомендуемое количество баллов = 9
// Вместе с предыдущими уроками = 33/47

/**
 * Пример
 *
 * Для заданного списка покупок `shoppingList` посчитать его общую стоимость
 * на основе цен из `costs`. В случае неизвестной цены считать, что товар
 * игнорируется.
 */
fun shoppingListCost(
    shoppingList: List<String>,
    costs: Map<String, Double>
): Double {
    var totalCost = 0.0

    for (item in shoppingList) {
        val itemCost = costs[item]
        if (itemCost != null) {
            totalCost += itemCost
        }
    }

    return totalCost
}

/**
 * Пример
 *
 * Для набора "имя"-"номер телефона" `phoneBook` оставить только такие пары,
 * для которых телефон начинается с заданного кода страны `countryCode`
 */
fun filterByCountryCode(
    phoneBook: MutableMap<String, String>,
    countryCode: String
) {
    val namesToRemove = mutableListOf<String>()

    for ((name, phone) in phoneBook) {
        if (!phone.startsWith(countryCode)) {
            namesToRemove.add(name)
        }
    }

    for (name in namesToRemove) {
        phoneBook.remove(name)
    }
}

/**
 * Пример
 *
 * Для заданного текста `text` убрать заданные слова-паразиты `fillerWords`
 * и вернуть отфильтрованный текст
 */
fun removeFillerWords(
    text: List<String>,
    vararg fillerWords: String
): List<String> {
    val fillerWordSet = setOf(*fillerWords)

    val res = mutableListOf<String>()
    for (word in text) {
        if (word !in fillerWordSet) {
            res += word
        }
    }
    return res
}

/**
 * Пример
 *
 * Для заданного текста `text` построить множество встречающихся в нем слов
 */
fun buildWordSet(text: List<String>): MutableSet<String> {
    val res = mutableSetOf<String>()
    for (word in text) res.add(word)
    return res
}


/**
 * Простая (2 балла)
 *
 * По заданному ассоциативному массиву "студент"-"оценка за экзамен" построить
 * обратный массив "оценка за экзамен"-"список студентов с этой оценкой".
 *
 * Например:
 *   buildGrades(mapOf("Марат" to 3, "Семён" to 5, "Михаил" to 5))
 *     -> mapOf(5 to listOf("Семён", "Михаил"), 3 to listOf("Марат"))
 */
fun buildGrades(grades: Map<String, Int>): Map<Int, List<String>> {
    val students = mutableMapOf<Int, List<String>>()
    for ((student, grade) in grades) {
        if (students[grade] == null)
            students[grade] = listOf(student)
        else students[grade] = students[grade]!! + student
    }
    return students
}

/**
 * Простая (2 балла)
 *
 * Определить, входит ли ассоциативный массив a в ассоциативный массив b;
 * это выполняется, если все ключи из a содержатся в b с такими же значениями.
 *
 * Например:
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "z", "b" to "sweet")) -> true
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "zee", "b" to "sweet")) -> false
 */
fun containsIn(a: Map<String, String>, b: Map<String, String>): Boolean {
    for (key in a.keys) {
        if (b[key] == null)
            return false
        if (b[key] != a[key])
            return false
    }
    return true
}

/**
 * Простая (2 балла)
 *
 * Удалить из изменяемого ассоциативного массива все записи,
 * которые встречаются в заданном ассоциативном массиве.
 * Записи считать одинаковыми, если и ключи, и значения совпадают.
 *
 * ВАЖНО: необходимо изменить переданный в качестве аргумента
 *        изменяемый ассоциативный массив
 *
 * Например:
 *   subtractOf(a = mutableMapOf("a" to "z"), mapOf("a" to "z"))
 *     -> a changes to mutableMapOf() aka becomes empty
 */
fun subtractOf(a: MutableMap<String, String>, b: Map<String, String>) {
    for (key in b.keys)
        if (a[key] != null && a[key] == b[key])
            a.remove(key)
}

/**
 * Простая (2 балла)
 *
 * Для двух списков людей найти людей, встречающихся в обоих списках.
 * В выходном списке не должно быть повторяюихся элементов,
 * т. е. whoAreInBoth(listOf("Марат", "Семён, "Марат"), listOf("Марат", "Марат")) == listOf("Марат")
 */
fun whoAreInBoth(a: List<String>, b: List<String>): List<String> {
    val result = mutableListOf<String>()
    for (name in a) {
        if (name in b && name !in result)
            result.add(name)
    }
    return result
}

/**
 * Средняя (3 балла)
 *
 * Объединить два ассоциативных массива `mapA` и `mapB` с парами
 * "имя"-"номер телефона" в итоговый ассоциативный массив, склеивая
 * значения для повторяющихся ключей через запятую.
 * В случае повторяющихся *ключей* значение из mapA должно быть
 * перед значением из mapB.
 *
 * Повторяющиеся *значения* следует добавлять только один раз.
 *
 * Например:
 *   mergePhoneBooks(
 *     mapOf("Emergency" to "112", "Police" to "02"),
 *     mapOf("Emergency" to "911", "Police" to "02")
 *   ) -> mapOf("Emergency" to "112, 911", "Police" to "02")
 */
fun mergePhoneBooks(mapA: Map<String, String>, mapB: Map<String, String>): Map<String, String> {
    val phones = mutableMapOf<String, List<String>>()
    for (key in mapA.keys) {
        if (phones[key] == null)
            phones[key] = listOf(mapA[key]!!)
        else if (phones[key] != listOf(mapA[key]) && mapA[key] !in phones[key]!!)
            phones[key] = phones[key]!! + mapA[key]!!
    }
    for (key in mapB.keys) {
        if (phones[key] == null)
            phones[key] = listOf(mapB[key]!!)
        else if (phones[key] != listOf(mapB[key]) && mapB[key] !in phones[key]!!)
            phones[key] = phones[key]!! + mapB[key]!!
    }
    val phones1 = mutableMapOf<String, String>()
    for (key in phones.keys) {
        phones1[key] = phones[key]!!.joinToString(", ")
    }
    return phones1
}

/**
 * Средняя (4 балла)
 *
 * Для заданного списка пар "акция"-"стоимость" вернуть ассоциативный массив,
 * содержащий для каждой акции ее усредненную стоимость.
 *
 * Например:
 *   averageStockPrice(listOf("MSFT" to 100.0, "MSFT" to 200.0, "NFLX" to 40.0))
 *     -> mapOf("MSFT" to 150.0, "NFLX" to 40.0)
 */
fun averageStockPrice(stockPrices: List<Pair<String, Double>>): Map<String, Double> {
    val prices = mutableMapOf<String, Double>()
    val quantity = mutableMapOf<String, Int>()
    for ((stock, price) in stockPrices) {
        quantity[stock] = (quantity[stock] ?: 0) + 1
        prices[stock] = (prices[stock] ?: 0.0) + price
    }
    for (stock in prices.keys) {
        prices[stock] = prices[stock]!! / quantity[stock]!!
    }
    return prices
}

/**
 * Средняя (4 балла)
 *
 * Входными данными является ассоциативный массив
 * "название товара"-"пара (тип товара, цена товара)"
 * и тип интересующего нас товара.
 * Необходимо вернуть название товара заданного типа с минимальной стоимостью
 * или null в случае, если товаров такого типа нет.
 *
 * Например:
 *   findCheapestStuff(
 *     mapOf("Мария" to ("печенье" to 20.0), "Орео" to ("печенье" to 100.0)),
 *     "печенье"
 *   ) -> "Мария"
 */
fun findCheapestStuff(stuff: Map<String, Pair<String, Double>>, kind: String): String? {
    val cheapestStuff = mutableMapOf<String, Pair<String, Double>>()
    for ((name, typeAndCost) in stuff) {
        val type = typeAndCost.first
        val cost = typeAndCost.second
        if (cheapestStuff[type] == null || cheapestStuff[type]!!.second > cost)
            cheapestStuff[type] = Pair(name, cost)
    }
    return cheapestStuff[kind]?.first
}

/**
 * Средняя (3 балла)
 *
 * Для заданного набора символов определить, можно ли составить из него
 * указанное слово (регистр символов игнорируется)
 *
 * Например:
 *   canBuildFrom(listOf('a', 'b', 'o'), "baobab") -> true
 */
fun canBuildFrom(chars: List<Char>, word: String): Boolean {
    for (char in word) {
        if (char.toLowerCase() !in chars && char.toUpperCase() !in chars)
            return false
    }
    return true
}

/**
 * Средняя (4 балла)
 *
 * Найти в заданном списке повторяющиеся элементы и вернуть
 * ассоциативный массив с информацией о числе повторений
 * для каждого повторяющегося элемента.
 * Если элемент встречается только один раз, включать его в результат
 * не следует.
 *
 * Например:
 *   extractRepeats(listOf("a", "b", "a")) -> mapOf("a" to 2)
 */
fun extractRepeats(list: List<String>): Map<String, Int> {
    val counterOfElements = mutableMapOf<String, Int>()
    val elementsToRemove = mutableListOf<String>()
    for (elem in list) {
        counterOfElements[elem] = (counterOfElements[elem] ?: 0) + 1
    }
    for ((elem, value) in counterOfElements) {
        if (value == 1) elementsToRemove.add(elem)
    }
    for (elem in elementsToRemove)
        counterOfElements.remove(elem)
    return counterOfElements
}

/**
 * Средняя (3 балла)
 *
 * Для заданного списка слов определить, содержит ли он анаграммы.
 * Два слова здесь считаются анаграммами, если они имеют одинаковую длину
 * и одно можно составить из второго перестановкой его букв.
 * Скажем, тор и рот или роза и азор это анаграммы,
 * а поле и полено -- нет.
 *
 * Например:
 *   hasAnagrams(listOf("тор", "свет", "рот")) -> true
 */
fun hasAnagrams(words: List<String>): Boolean {
    var flag = false
    for (word in words) {
        for (otherWord in words - word) {
            if (word.isEmpty() || otherWord.isEmpty()) return true
            if (otherWord.length == word.length) {
                for (letter in word) {
                    if (letter !in otherWord) {
                        flag = false
                        break
                    }
                    flag = true
                }
            }
            if (flag) return true
        }
    }
    return false
}

/**
 * Сложная (5 баллов)
 *
 * Для заданного ассоциативного массива знакомых через одно рукопожатие `friends`
 * необходимо построить его максимальное расширение по рукопожатиям, то есть,
 * для каждого человека найти всех людей, с которыми он знаком через любое
 * количество рукопожатий.
 *
 * Считать, что все имена людей являются уникальными, а также что рукопожатия
 * являются направленными, то есть, если Марат знает Свету, то это не означает,
 * что Света знает Марата.
 *
 * Оставлять пустой список знакомых для людей, которые их не имеют (см. EvilGnome ниже),
 * в том числе для случая, когда данного человека нет в ключах, но он есть в значениях
 * (см. GoodGnome ниже).
 *
 * Например:
 *   propagateHandshakes(
 *     mapOf(
 *       "Marat" to setOf("Mikhail", "Sveta"),
 *       "Sveta" to setOf("Marat"),
 *       "Mikhail" to setOf("Sveta"),
 *       "Friend" to setOf("GoodGnome"),
 *       "EvilGnome" to setOf()
 *     )
 *   ) -> mapOf(
 *          "Marat" to setOf("Mikhail", "Sveta"),
 *          "Sveta" to setOf("Marat", "Mikhail"),
 *          "Mikhail" to setOf("Sveta", "Marat"),
 *          "Friend" to setOf("GoodGnome"),
 *          "EvilGnome" to setOf(),
 *          "GoodGnome" to setOf()
 *        )
 */
fun propagateHandshakes(friends: Map<String, Set<String>>): Map<String, Set<String>> {
    val handshakes = mutableMapOf<String, Set<String>>()
    fun findFriends(name1: String, friendSet: Set<String>, name2: String): Set<String> {
        val setOfFriends = mutableSetOf<String>()
        if (name1 in friends.keys && !friends[name1].isNullOrEmpty())
            for (friend in friends.getValue(name1)) {
                if (friend !in friendSet && friend != name2) {
                    setOfFriends += friend
                    for (friends1 in findFriends(friend, setOfFriends.union(friendSet), name2))
                        setOfFriends += friends1
                }
            }
        return setOfFriends
    }
    for ((name, _) in friends) {
        val set1 = setOf<String>()
        handshakes[name] = findFriends(name, set1, name).reversed().toSet()
    }
    for (setOfFriends in friends.values)
        for (friend in setOfFriends)
            if (friend !in handshakes.keys)
                handshakes[friend] = setOf()
    return handshakes
}

/**
 * Сложная (6 баллов)
 *
 * Для заданного списка неотрицательных чисел и числа определить,
 * есть ли в списке пара чисел таких, что их сумма равна заданному числу.
 * Если да, верните их индексы в виде Pair<Int, Int>;
 * если нет, верните пару Pair(-1, -1).
 *
 * Индексы в результате должны следовать в порядке (меньший, больший).
 *
 * Постарайтесь сделать ваше решение как можно более эффективным,
 * используя то, что вы узнали в данном уроке.
 *
 * Например:
 *   findSumOfTwo(listOf(1, 2, 3), 4) -> Pair(0, 2)
 *   findSumOfTwo(listOf(1, 2, 3), 6) -> Pair(-1, -1)
 */
fun findSumOfTwo(list: List<Int>, number: Int): Pair<Int, Int> {
    val rem = mutableMapOf<Int, Int>()
    for (i in list.indices) {
        if (list[i] in rem.keys)
            return Pair(rem[list[i]]!!, i)
        else if (number - list[i] !in rem.keys)
            rem[number - list[i]] = i
    }
    return Pair(-1, -1)
}

/**
 * Очень сложная (8 баллов)
 *
 * Входными данными является ассоциативный массив
 * "название сокровища"-"пара (вес сокровища, цена сокровища)"
 * и вместимость вашего рюкзака.
 * Необходимо вернуть множество сокровищ с максимальной суммарной стоимостью,
 * которые вы можете унести в рюкзаке.
 *
 * Перед решением этой задачи лучше прочитать статью Википедии "Динамическое программирование".
 *
 * Например:
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     850
 *   ) -> setOf("Кубок")
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     450
 *   ) -> emptySet()
 */
fun bagPacking(treasures: Map<String, Pair<Int, Int>>, capacity: Int): Set<String> {
    val table = Array(treasures.size + 1) { Array(capacity + 1) { Pair(0, setOf<String>()) } }
    val names = mutableListOf<Set<String>>()
    val weights = mutableListOf<Int>()
    val costs = mutableListOf<Int>()
    var result = mutableSetOf<String>()
    var maxRes = 0
    for ((name, weightAndCost) in treasures) {
        weights.add(weightAndCost.first)
        costs.add(weightAndCost.second)
        names.add(setOf(name))
    }
    for (i in 1..treasures.size)
        for (j in 0..capacity) {
            table[i][j] = table[i - 1][j]
            if (j >= weights[i - 1] && table[i - 1][j].first < table[i - 1][j - weights[i - 1]].first + costs[i - 1])
                table[i][j] = Pair(
                    table[i - 1][j - weights[i - 1]].first + costs[i - 1],
                    table[i - 1][j - weights[i - 1]].second.union(names[i - 1])
                )
            if (table[i][j].first > maxRes) {
                maxRes = table[i][j].first
                result.clear()
                result = result.union(table[i][j].second) as MutableSet<String>
            }
        }
    return result
}