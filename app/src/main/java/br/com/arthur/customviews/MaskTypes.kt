package br.com.arthur.customviews

enum class MaskTypes
/**
 *
 * @param maskName Mask name
 * @param maskFormat Mask Format
 * @param unmaskCount Text length with no masked
 * @param maskCount Text length with mask
 */
constructor(
    val id: Int,
    val maskName: String,
    val maskFormat: String,
    val unmaskCount: Long,
    val maskCount: Long
) {
    CPF(1, "CPF", "###.###.###-##", 11, 14),
    CEP(2, "CEP", "#####-###", 8, 9),
    CELLPHONE_MASK(3, "Cell Phone Mask", "(##) #####-####", 11, 12),
    PHONE_MASK(4, "Phone Mask", "### #### ####", 11, 13),
    CREDIT_CARD_NUMBER_MASK(5, "Credit Card Number Mask", "#### #### #### ####", 16, 19),
    CREDIT_CARD_EXPIRATION_MASK(6, "Credit Card Expiration Mask", "##/####", 6, 7),
    DATE(7, "Data", "##/##/####", 8, 10)
}
