<p align="center">
  <img src="https://www.corda.net/wp-content/uploads/2016/11/fg005_corda_b.png" alt="Corda" width="500">
</p>

# CorDapp SerializationTest - Kotlin

This is a test project to demonstrate an issue. To replicate the issue, toggle the value of "cordaVersion" in constants.properties.

To observe the issue, run the tests in clients/src/kotlin/test/com/serializationtest/client/jackson/Test.kt

A cordaVersion value of
- 4.1 = no issue
- 4.3 = issue observed.

