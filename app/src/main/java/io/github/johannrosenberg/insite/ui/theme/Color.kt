package io.github.johannrosenberg.insite.ui.theme

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

object AppColors {
    var useLightTheme = true

    val bottomTabIndicator: Color
        get() {
            return MaterialColors.lightBlue100
        }

    val bottomTabIconTextSelected: Color
        get() {
            return MaterialColors.blue100
        }

    val toolbarTitle: Color
        get() {
            return MaterialColors.white
        }

    val toolbarIcon: Color
        get() {
            return MaterialColors.cyan300
        }

    val toolbarNavIcon: Color
        get() {
            return MaterialColors.white
        }


    val snackbarNormalBg: Color
        get() {
            return if (useLightTheme) Color(0xDC146CD6) else MaterialColors.blue700
        }

    val snackbarNormalText: Color
        get() {
            return if (useLightTheme) Color.White else Color.White
        }

    val snackbarCriticalBg: Color
        get() {
            return if (useLightTheme) Color(0xFFE41717) else Color(0xFFFF4B4B)
        }

    val snackbarCriticalText: Color
        get() {
            return if (useLightTheme) Color.White else Color.White
        }

    val snackbarNormalAction: Color
        get() {
            return if (useLightTheme) Color.White else Color.White
        }

    val snackbarCriticalAction: Color
        get() {
            return if (useLightTheme) Color(0xFFFFCBCB) else Color.White
        }

    // Navigation menu
    val navigationIconTint: Color
        get() {
            return if (useLightTheme) MaterialColors.red500 else MaterialColors.blueGray400
        }

    val navigationItemUnselectedContainerColor: Color
        get() {
            return if (useLightTheme) Color.White else Color.Black
        }

    val navigationItemSelectedContainerColor: Color
        get() {
            return Color(0xCBD2E3EB)
        }

    val navigationItemSelectedTextColor: Color
        get() {
            return MaterialColors.blueGray900
        }

    val navigationItemSelectedIconColor: Color
        get() {
            return MaterialColors.green600
        }

    val navigationItemUnselectedIconColor: Color
        get() {
            return MaterialColors.yellow300
        }

    // Tint for icons.
    val tintForIconsWithBackgroundColor: Color
        get() {
            return MaterialColors.white
        }

    val tintForIconsWithNoBackgroundColor: Color
        get() {
            return if (useLightTheme) MaterialColors.gray600 else MaterialColors.gray100
        }

    val defaultButtonColors: ButtonColors
        get() {
            return ButtonColors(
                containerColor = MaterialColors.cyan500,
                contentColor = MaterialColors.white,
                disabledContainerColor = MaterialColors.gray300,
                disabledContentColor = MaterialColors.gray700
            )
        }

    val cancelButtonColors: ButtonColors
        get() {
            return defaultButtonColors.copy(containerColor = MaterialColors.red400)
        }

    val challengeLevelEasyText: Color
        get() {
            return MaterialColors.tealA200
        }

    val challengeLevelModerateText: Color
        get() {
            return MaterialColors.yellow300
        }

    val challengeLevelHardText: Color
        get() {
            return MaterialColors.red200
        }


    lateinit var defaultSwitchColors: SwitchColors

    @Composable
    fun InitializeComposableColors() {
        defaultSwitchColors = SwitchDefaults.colors(
            checkedThumbColor = MaterialColors.blue700,
            checkedTrackColor = MaterialColors.blue100,
            uncheckedThumbColor = MaterialColors.gray400,
            uncheckedTrackColor = MaterialColors.gray100,
        )
    }
}

class MaterialColors {
    companion object {
        // Red
        val red50 = Color(0xFFFFEBEE)
        val red100 = Color(0xFFFFCDD2)
        val red200 = Color(0xFFEF9A9A)
        val red300 = Color(0xFFE57373)
        val red400 = Color(0xFFEF5350)
        val red500 = Color(0xFFF44336)
        val red600 = Color(0xFFE53935)
        val red700 = Color(0xFFD32F2F)
        val red800 = Color(0xFFC62828)
        val red900 = Color(0xFFB71C1C)
        val redA100 = Color(0xFFFF8A80)
        val redA200 = Color(0xFFFF5252)
        val redA400 = Color(0xFFFF1744)
        val redA700 = Color(0xFFD50000)

        // Pink
        val pink50 = Color(0xFFFCE4EC)
        val pink100 = Color(0xFFF8BBD0)
        val pink200 = Color(0xFFF48FB1)
        val pink300 = Color(0xFFF06292)
        val pink400 = Color(0xFFEC407A)
        val pink500 = Color(0xFFE91E63)
        val pink600 = Color(0xFFD81B60)
        val pink700 = Color(0xFFC2185B)
        val pink800 = Color(0xFFAD1457)
        val pink900 = Color(0xFF880E4F)
        val pinkA100 = Color(0xFFFF80AB)
        val pinkA200 = Color(0xFFFF4081)
        val pinkA400 = Color(0xFFF50057)
        val pinkA700 = Color(0xFFC51162)

        // Purple
        val purple50 = Color(0xFFF3E5F5)
        val purple100 = Color(0xFFE1BEE7)
        val purple200 = Color(0xFFCE93D8)
        val purple300 = Color(0xFFBA68C8)
        val purple400 = Color(0xFFAB47BC)
        val purple500 = Color(0xFF9C27B0)
        val purple600 = Color(0xFF8E24AA)
        val purple700 = Color(0xFF7B1FA2)
        val purple800 = Color(0xFF6A1B9A)
        val purple900 = Color(0xFF4A148C)
        val purpleA100 = Color(0xFFEA80FC)
        val purpleA200 = Color(0xFFE040FB)
        val purpleA400 = Color(0xFFD500F9)
        val purpleA700 = Color(0xFFAA00FF)

        // Deep Purple
        val deepPurple50 = Color(0xFFEDE7F6)
        val deepPurple100 = Color(0xFFD1C4E9)
        val deepPurple200 = Color(0xFFB39DDB)
        val deepPurple300 = Color(0xFF9575CD)
        val deepPurple400 = Color(0xFF7E57C2)
        val deepPurple500 = Color(0xFF673AB7)
        val deepPurple600 = Color(0xFF5E35B1)
        val deepPurple700 = Color(0xFF512DA8)
        val deepPurple800 = Color(0xFF4527A0)
        val deepPurple900 = Color(0xFF311B92)
        val deepPurpleA100 = Color(0xFFB388FF)
        val deepPurpleA200 = Color(0xFF7C4DFF)
        val deepPurpleA400 = Color(0xFF651FFF)
        val deepPurpleA700 = Color(0xFF6200EA)

        // Indigo
        val indigo50 = Color(0xFFE8EAF6)
        val indigo100 = Color(0xFFC5CAE9)
        val indigo200 = Color(0xFF9FA8DA)
        val indigo300 = Color(0xFF7986CB)
        val indigo400 = Color(0xFF5C6BC0)
        val indigo500 = Color(0xFF3F51B5)
        val indigo600 = Color(0xFF3949AB)
        val indigo700 = Color(0xFF303F9F)
        val indigo800 = Color(0xFF283593)
        val indigo900 = Color(0xFF1A237E)
        val indigoA100 = Color(0xFF8C9EFF)
        val indigoA200 = Color(0xFF536DFE)
        val indigoA400 = Color(0xFF3D5AFE)
        val indigoA700 = Color(0xFF304FFE)

        // Blue
        val blue50 = Color(0xFFE3F2FD)
        val blue100 = Color(0xFFBBDEFB)
        val blue200 = Color(0xFF90CAF9)
        val blue300 = Color(0xFF64B5F6)
        val blue400 = Color(0xFF42A5F5)
        val blue500 = Color(0xFF2196F3)
        val blue600 = Color(0xFF1E88E5)
        val blue700 = Color(0xFF1976D2)
        val blue800 = Color(0xFF1565C0)
        val blue900 = Color(0xFF0D47A1)
        val blueA100 = Color(0xFF82B1FF)
        val blueA200 = Color(0xFF448AFF)
        val blueA400 = Color(0xFF2979FF)
        val blueA700 = Color(0xFF2962FF)

        val lightBlue123 = Color(0x75B3E5FC)

        // Light Blue
        val lightBlue50 = Color(0xFFE1F5FE)
        val lightBlue100 = Color(0xFFB3E5FC)
        val lightBlue200 = Color(0xFF81D4FA)
        val lightBlue300 = Color(0xFF4FC3F7)
        val lightBlue400 = Color(0xFF29B6F6)
        val lightBlue500 = Color(0xFF03A9F4)
        val lightBlue600 = Color(0xFF039BE5)
        val lightBlue700 = Color(0xFF0288D1)
        val lightBlue800 = Color(0xFF0277BD)
        val lightBlue900 = Color(0xFF01579B)
        val lightBlueA100 = Color(0xFF80D8FF)
        val lightBlueA200 = Color(0xFF40C4FF)
        val lightBlueA400 = Color(0xFF00B0FF)
        val lightBlueA700 = Color(0xFF0091EA)

        // Cyan
        val cyan50 = Color(0xFFE0F7FA)
        val cyan100 = Color(0xFFB2EBF2)
        val cyan200 = Color(0xFF80DEEA)
        val cyan300 = Color(0xFF4DD0E1)
        val cyan400 = Color(0xFF26C6DA)
        val cyan500 = Color(0xFF00BCD4)
        val cyan600 = Color(0xFF00ACC1)
        val cyan700 = Color(0xFF0097A7)
        val cyan800 = Color(0xFF00838F)
        val cyan900 = Color(0xFF006064)
        val cyanA100 = Color(0xFF84FFFF)
        val cyanA200 = Color(0xFF18FFFF)
        val cyanA400 = Color(0xFF00E5FF)
        val cyanA700 = Color(0xFF00B8D4)

        // Teal
        val teal50 = Color(0xFFE0F2F1)
        val teal100 = Color(0xFFB2DFDB)
        val teal200 = Color(0xFF80CBC4)
        val teal300 = Color(0xFF4DB6AC)
        val teal400 = Color(0xFF26A69A)
        val teal500 = Color(0xFF009688)
        val teal600 = Color(0xFF00897B)
        val teal700 = Color(0xFF00796B)
        val teal800 = Color(0xFF00695C)
        val teal900 = Color(0xFF004D40)
        val tealA100 = Color(0xFFA7FFEB)
        val tealA200 = Color(0xFF64FFDA)
        val tealA400 = Color(0xFF1DE9B6)
        val tealA700 = Color(0xFF00BFA5)

        // Green
        val green50 = Color(0xFFE8F5E9)
        val green100 = Color(0xFFC8E6C9)
        val green200 = Color(0xFFA5D6A7)
        val green300 = Color(0xFF81C784)
        val green400 = Color(0xFF66BB6A)
        val green500 = Color(0xFF4CAF50)
        val green600 = Color(0xFF43A047)
        val green700 = Color(0xFF388E3C)
        val green800 = Color(0xFF2E7D32)
        val green900 = Color(0xFF1B5E20)
        val greenA100 = Color(0xFFB9F6CA)
        val greenA200 = Color(0xFF69F0AE)
        val greenA400 = Color(0xFF00E676)
        val greenA700 = Color(0xFF00C853)

        // Light Green
        val lightGreen50 = Color(0xFFF1F8E9)
        val lightGreen100 = Color(0xFFDCEDC8)
        val lightGreen200 = Color(0xFFC5E1A5)
        val lightGreen300 = Color(0xFFAED581)
        val lightGreen400 = Color(0xFF9CCC65)
        val lightGreen500 = Color(0xFF8BC34A)
        val lightGreen600 = Color(0xFF7CB342)
        val lightGreen700 = Color(0xFF689F38)
        val lightGreen800 = Color(0xFF558B2F)
        val lightGreen900 = Color(0xFF33691E)
        val lightGreenA100 = Color(0xFFCCFF90)
        val lightGreenA200 = Color(0xFFB2FF59)
        val lightGreenA400 = Color(0xFF76FF03)
        val lightGreenA700 = Color(0xFF64DD17)

        // Lime
        val lime50 = Color(0xFFF9FBE7)
        val lime100 = Color(0xFFF0F4C3)
        val lime200 = Color(0xFFE6EE9C)
        val lime300 = Color(0xFFDCE775)
        val lime400 = Color(0xFFD4E157)
        val lime500 = Color(0xFFCDDC39)
        val lime600 = Color(0xFFC0CA33)
        val lime700 = Color(0xFFAFB42B)
        val lime800 = Color(0xFF9E9D24)
        val lime900 = Color(0xFF827717)
        val limeA100 = Color(0xFFF4FF81)
        val limeA200 = Color(0xFFEEFF41)
        val limeA400 = Color(0xFFC6FF00)
        val limeA700 = Color(0xFFAEEA00)

        // Yellow
        val yellow50 = Color(0xFFFFFDE7)
        val yellow100 = Color(0xFFFFF9C4)
        val yellow200 = Color(0xFFFFF59D)
        val yellow300 = Color(0xFFFFF176)
        val yellow400 = Color(0xFFFFEE58)
        val yellow500 = Color(0xFFFFEB3B)
        val yellow600 = Color(0xFFFDD835)
        val yellow700 = Color(0xFFFBC02D)
        val yellow800 = Color(0xFFF9A825)
        val yellow900 = Color(0xFFF57F17)
        val yellowA100 = Color(0xFFFFFF8D)
        val yellowA200 = Color(0xFFFFFF00)
        val yellowA400 = Color(0xFFFFEA00)
        val yellowA700 = Color(0xFFFFD600)

        // Amber
        val amber50 = Color(0xFFFFF8E1)
        val amber100 = Color(0xFFFFECB3)
        val amber200 = Color(0xFFFFE082)
        val amber300 = Color(0xFFFFD54F)
        val amber400 = Color(0xFFFFCA28)
        val amber500 = Color(0xFFFFC107)
        val amber600 = Color(0xFFFFB300)
        val amber700 = Color(0xFFFFA000)
        val amber800 = Color(0xFFFF8F00)
        val amber900 = Color(0xFFFF6F00)
        val amberA100 = Color(0xFFFFE57F)
        val amberA200 = Color(0xFFFFD740)
        val amberA400 = Color(0xFFFFC400)
        val amberA700 = Color(0xFFFFAB00)

        // Orange
        val orange50 = Color(0xFFFFF3E0)
        val orange100 = Color(0xFFFFE0B2)
        val orange200 = Color(0xFFFFCC80)
        val orange300 = Color(0xFFFFB74D)
        val orange400 = Color(0xFFFFA726)
        val orange500 = Color(0xFFFF9800)
        val orange600 = Color(0xFFFB8C00)
        val orange700 = Color(0xFFF57C00)
        val orange800 = Color(0xFFEF6C00)
        val orange900 = Color(0xFFE65100)
        val orangeA100 = Color(0xFFFFD180)
        val orangeA200 = Color(0xFFFFAB40)
        val orangeA400 = Color(0xFFFF9100)
        val orangeA700 = Color(0xFFFF6D00)

        // Deep Orange
        val deepOrange50 = Color(0xFFFBE9E7)
        val deepOrange100 = Color(0xFFFFCCBC)
        val deepOrange200 = Color(0xFFFFAB91)
        val deepOrange300 = Color(0xFFFF8A65)
        val deepOrange400 = Color(0xFFFF7043)
        val deepOrange500 = Color(0xFFFF5722)
        val deepOrange600 = Color(0xFFF4511E)
        val deepOrange700 = Color(0xFFE64A19)
        val deepOrange800 = Color(0xFFD84315)
        val deepOrange900 = Color(0xFFBF360C)
        val deepOrangeA100 = Color(0xFFFF9E80)
        val deepOrangeA200 = Color(0xFFFF6E40)
        val deepOrangeA400 = Color(0xFFFF3D00)
        val deepOrangeA700 = Color(0xFFDD2C00)

        // Brown
        val brown50 = Color(0xFFEFEBE9)
        val brown100 = Color(0xFFD7CCC8)
        val brown200 = Color(0xFFBCAAA4)
        val brown300 = Color(0xFFA1887F)
        val brown400 = Color(0xFF8D6E63)
        val brown500 = Color(0xFF795548)
        val brown600 = Color(0xFF6D4C41)
        val brown700 = Color(0xFF5D4037)
        val brown800 = Color(0xFF4E342E)
        val brown900 = Color(0xFF3E2723)

        // Gray
        val gray50 = Color(0xFFFAFAFA)
        val gray100 = Color(0xFFF5F5F5)
        val gray200 = Color(0xFFEEEEEE)
        val gray300 = Color(0xFFE0E0E0)
        val gray400 = Color(0xFFBDBDBD)
        val gray500 = Color(0xFF9E9E9E)
        val gray600 = Color(0xFF757575)
        val gray700 = Color(0xFF616161)
        val gray800 = Color(0xFF424242)
        val gray900 = Color(0xFF212121)

        // Blue Gray
        val blueGray50 = Color(0xFFECEFF1)
        val blueGray100 = Color(0xFFCFD8DC)
        val blueGray200 = Color(0xFFB0BEC5)
        val blueGray300 = Color(0xFF90A4AE)
        val blueGray400 = Color(0xFF78909C)
        val blueGray500 = Color(0xFF607D8B)
        val blueGray600 = Color(0xFF546E7A)
        val blueGray700 = Color(0xFF455A64)
        val blueGray800 = Color(0xFF37474F)
        val blueGray900 = Color(0xFF263238)

        // Black
        val black = Color(0xFF000000)

        // White
        val white = Color(0xFFFFFFFF)
    }
}