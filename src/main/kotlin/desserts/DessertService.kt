package desserts

import kotliquery.HikariCP
import kotliquery.queryOf;
import kotliquery.sessionOf

data class Dessert(
    val id: String,
    val name: String,
    val calories: Int,
    val fat: Double,
    val carbs: Int,
    val proteins: Double
)

class DessertService {

    fun selectAll(): List<Dessert> {
        val qry = queryOf("""
            SELECT UUID_TO_CHAR(ID), DNAME, CALORIES, FAT, CARBS, PROTEIN
            FROM DESSERTS""".trimIndent())
            .map { row -> Dessert(
                id = row.string("ID"),
                name = row.string("DNAME"),
                calories = row.int("calories"),
                fat = row.double("FAT"),
                carbs = row.int("CARBS"),
                proteins = row.double("PROTEIN")
            )}.asList
        val result :List<Dessert>
        sessionOf(HikariCP.dataSource()).use { conn ->
            result = conn.run(qry)
        }
        return result
    }

}