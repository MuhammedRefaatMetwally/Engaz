package com.example.engaz.features.wallet.view.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.methods.response.EthAccounts
import org.web3j.protocol.http.HttpService
import javax.inject.Inject

@HiltViewModel
class EthereumViewModel @Inject constructor() : ViewModel() {

    //private val web3 = Web3j.build(HttpService("https://mainnet.infura.io/v3/YOUR_INFURA_PROJECT_ID"))

    private lateinit var web3j: Web3j

    init {
        val httpService = HttpService("https://eth-sepolia.g.alchemy.com/v2/DnY8WJnH3sdylRTV04_l-sDJdnATGgK4")
        web3j = Web3j.build(httpService)
    }

    suspend fun connectMetaMask(): String {
        val accounts: EthAccounts =
            withContext(Dispatchers.IO) {
                web3j.ethAccounts().sendAsync().get()
            }
        return accounts.accounts[0]
 }

}
