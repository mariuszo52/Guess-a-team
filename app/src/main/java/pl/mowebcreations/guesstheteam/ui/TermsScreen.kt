package pl.mowebcreations.guesstheteam.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import pl.mowebcreations.guesstheteam.AdViewBanner
import pl.mowebcreations.guesstheteam.R

@Composable
fun TermsScreen(
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .background(color = Color.White),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .height(70.dp)

            ) {
                Button(
                    modifier = Modifier
                        .width(60.dp),
                    contentPadding = PaddingValues(10.dp),
                    colors = ButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Black,
                        disabledContentColor = Color.Gray,
                        disabledContainerColor = Color.DarkGray
                    ),

                    onClick = { onBackClick() }
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxSize(),
                        painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                        contentDescription = null
                    )
                }
                Text(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    text = "Warunki korzystania"
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.93f)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                color = Color.Black,
                modifier = Modifier
                    .fillMaxSize(),
                textAlign = TextAlign.Center,
                text = "1. Wstęp\n" +
                        "Niniejsze Warunki Korzystania regulują zasady korzystania z aplikacji mobilnej \"Odgadnij Drużynę\" (dalej \"Aplikacja\"). Przed rozpoczęciem korzystania z Aplikacji prosimy o uważne zapoznanie się z niniejszymi warunkami. Korzystając z Aplikacji, zgadzasz się na przestrzeganie tych warunków. Jeśli nie zgadzasz się z którymkolwiek z poniższych warunków, prosimy o niekorzystanie z Aplikacji.\n" +
                        "\n" +
                        "2. Dostępność i Użytkowanie\n" +
                        "2.1. Aplikacja jest dostępna do pobrania i użytkowania na urządzeniach mobilnych zgodnych z wymaganiami technicznymi określonymi w sklepie aplikacji.\n" +
                        "\n" +
                        "2.2. Użytkownik zobowiązuje się korzystać z Aplikacji zgodnie z obowiązującymi przepisami prawa, zasadami współżycia społecznego oraz niniejszymi Warunkami Korzystania.\n" +
                        "\n" +
                        "3. Rejestracja i Konto Użytkownika\n" +
                        "3.1. Korzystanie z niektórych funkcji Aplikacji może wymagać rejestracji i utworzenia konta użytkownika.\n" +
                        "\n" +
                        "3.2. Użytkownik zobowiązuje się do podawania prawdziwych i aktualnych informacji podczas rejestracji oraz do aktualizowania swoich danych w razie potrzeby.\n" +
                        "\n" +
                        "3.3. Użytkownik jest odpowiedzialny za zachowanie poufności swoich danych logowania oraz za wszelkie działania podejmowane na jego koncie.\n" +
                        "\n" +
                        "4. Treści i Własność Intelektualna\n" +
                        "4.1. Wszystkie treści dostępne w Aplikacji, w tym teksty, grafiki, logo, ikony, obrazy, nagrania audio i wideo, są własnością właściciela Aplikacji lub są udostępniane na licencji.\n" +
                        "\n" +
                        "4.2. Użytkownik nie ma prawa do kopiowania, modyfikowania, rozpowszechniania ani wykorzystywania w inny sposób treści dostępnych w Aplikacji bez uprzedniej pisemnej zgody właściciela Aplikacji.\n" +
                        "\n" +
                        "5. Płatności\n" +
                        "5.1. Aplikacja może oferować zakupy wewnątrz aplikacji (in-app purchases), w tym funkcje premium, dodatkowe poziomy gry lub inne treści cyfrowe.\n" +
                        "\n" +
                        "5.2. Wszystkie płatności są przetwarzane za pośrednictwem odpowiedniego sklepu z aplikacjami (Google Play, App Store). Warunki korzystania z tych usług podlegają regulaminom odpowiednich sklepów.\n" +
                        "\n" +
                        "6. Polityka Prywatności\n" +
                        "6.1. Korzystanie z Aplikacji jest regulowane Polityką Prywatności, która określa zasady przetwarzania danych osobowych użytkowników. Polityka Prywatności dostępna jest tutaj.\n" +
                        "\n" +
                        "7. Odpowiedzialność\n" +
                        "7.1. Właściciel Aplikacji dokłada wszelkich starań, aby zapewnić prawidłowe działanie Aplikacji, jednak nie gwarantuje, że będzie ona wolna od błędów czy przerw w działaniu.\n" +
                        "\n" +
                        "7.2. Właściciel Aplikacji nie ponosi odpowiedzialności za jakiekolwiek szkody, które mogą wyniknąć z korzystania z Aplikacji, w tym za utratę danych, uszkodzenie urządzenia lub inne szkody pośrednie.\n" +
                        "\n" +
                        "8. Modyfikacje Warunków Korzystania\n" +
                        "8.1. Właściciel Aplikacji zastrzega sobie prawo do wprowadzania zmian w niniejszych Warunkach Korzystania w dowolnym momencie. O wszelkich zmianach Użytkownik zostanie poinformowany poprzez aktualizację warunków w Aplikacji.\n" +
                        "\n" +
                        "8.2. Dalsze korzystanie z Aplikacji po wprowadzeniu zmian w Warunkach Korzystania oznacza akceptację tych zmian.\n" +
                        "\n" +
                        "9. Kontakt\n" +
                        "9.1. W razie jakichkolwiek pytań lub wątpliwości dotyczących niniejszych Warunków Korzystania, prosimy o kontakt pod adresem e-mail: [mariusz52ozga@gmail.com].\n" +
                        "\n"
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxSize()) {
            AdViewBanner(
                modifier = Modifier
                    .fillMaxWidth(),
                addUnitId = stringResource(id = R.string.banner_unit_id)
            )

        }
    }
}