import {ERROR_MESSAGE, EVENT_TYPE} from '../../utils/constants.js'
import api from '../../api/index.js'

function Login() {
  const $loginButton = document.querySelector('#login-button')
  const onLogin = event => {
    event.preventDefault()

    const emailValue = document.querySelector('#email').value
    const passwordValue = document.querySelector('#password').value

      if (!emailValue && !passwordValue) {
      Snackbar.show({ text: ERROR_MESSAGE.LOGIN_FAIL, pos: 'bottom-center', showAction: false, duration: 2000 })
      return
    }

      const loginRequest = {
          email: emailValue,
          password: passwordValue
      }

      api.oauth.login(loginRequest)
          .then(data => {
              localStorage.setItem("token", data.accessToken);
              window.location.href = "/";
          })
          .catch(error => console.log(error));
  }

  this.init = () => {
    $loginButton.addEventListener(EVENT_TYPE.CLICK, onLogin)
  }
}

const login = new Login()
login.init()
