package com.serializationtest.states

import com.serializationtest.contracts.SerializationTestContract
import net.corda.core.contracts.BelongsToContract
import net.corda.core.contracts.ContractState
import net.corda.core.identity.AbstractParty

// *********
// * State *
// *********
@BelongsToContract(SerializationTestContract::class)
data class SerializationTestState(val data: String, override val participants: List<AbstractParty> = listOf()) : ContractState
