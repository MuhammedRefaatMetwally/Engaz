package com.example.engaz.features.wallet.view.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext

import io.metamask.androidsdk.Dapp
import io.metamask.androidsdk.Ethereum
import io.metamask.androidsdk.EthereumRequest
import io.metamask.androidsdk.EthereumState
import io.metamask.androidsdk.RequestError
import io.metamask.androidsdk.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.methods.response.EthAccounts
import org.web3j.protocol.http.HttpService
import javax.inject.Inject

@HiltViewModel
class EthereumViewModel @Inject constructor(
    @ApplicationContext val  context: Context
) : ViewModel() {

    //private val web3 = Web3j.build(HttpService("https://mainnet.infura.io/v3/YOUR_INFURA_PROJECT_ID"))
    val ethereum = Ethereum(context)

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

        return if (accounts.accounts.isNotEmpty()) {
            accounts.accounts[0]
        } else {
            Log.e("TAG", "No accounts found")
            ""
        }
 }

    val ethereumState = MediatorLiveData<EthereumState>().apply {
        addSource(ethereum.ethereumState) { newEthereumState ->
            value = newEthereumState
        }
    }

    // Wrapper function to connect the dapp.
    fun connect(dapp: Dapp, callback: ((Any?) -> Unit)?) {
        ethereum.connect(dapp, callback)
    }

    // Wrapper function call all RPC methods.
    fun sendRequest(request: EthereumRequest, callback: ((Any?) -> Unit)?) {
        ethereum.sendRequest(request, callback)
    }



}
