package com.mark.baubapchallenge.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mark.baubapchallenge.R
import com.mark.baubapchallenge.domain.authentication.model.UsernamePasswordCredentials
import com.mark.baubapchallenge.domain.model.base.State
import com.mark.baubapchallenge.view.ui.composable.CircularLoader
import com.mark.baubapchallenge.view.ui.composable.EditableField
import com.mark.baubapchallenge.viewmodel.LoginInternalViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Login Activity
 */
@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    private val viewModel: LoginInternalViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { LoginView(viewModel) }
    }

    private fun executeOnResult(state: State<Any>) {
        val text = when (state) {
            is State.Result -> this.getString(R.string.login_successful)
            is State.Error -> this.getString(R.string.login_error)
            else -> null
        }
        text?.let {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    @Composable
    fun LoginView(viewModel: LoginInternalViewModel) {
        Surface {
            var credentials by remember { mutableStateOf(UsernamePasswordCredentials()) }
            var showProgress by remember { mutableStateOf(false) }
            val context = LocalContext.current

            val loginState = viewModel.loginState.collectAsStateWithLifecycle()
            showProgress = if (loginState.value is State.Loading) {
                credentials = credentials.copy("", "")
                true
            } else {
                false
            }
            executeOnResult(loginState.value)
            if (showProgress) {
                CircularLoader()
            } else {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 50.dp)
                ) {
                    Text(
                        context.getString(R.string.login_title),
                        fontSize = 27.sp,
                        style = MaterialTheme.typography.displayLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(90.dp))
                    EditableField(
                        value = credentials.username,
                        onChange = { data ->
                            credentials = credentials.copy(username = data)
                        },
                        placeholder = context.getString(R.string.login_view_username),
                        label = context.getString(R.string.login_view_username),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    EditableField(
                        value = credentials.password,
                        onChange = { data ->
                            credentials = credentials.copy(password = data)
                        },
                        placeholder = context.getString(R.string.login_view_password),
                        label = context.getString(R.string.login_view_password),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    Button(
                        onClick = {
                            if (credentials.isValid()) {
                                viewModel.login(credentials)
                            } else {
                                Toast.makeText(
                                    context,
                                    context.getString(R.string.issues_with_credentials),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        enabled = true,
                        shape = RoundedCornerShape(7.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(context.getString(R.string.login_btn), fontSize = 20.sp)
                    }
                }
            }
        }
    }
}