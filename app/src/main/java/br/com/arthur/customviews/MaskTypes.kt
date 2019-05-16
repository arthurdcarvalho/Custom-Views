package br.com.arthur.customviews

enum class MaskTypes
/**
 *
 * @param maskName Mask name
 * @param maskFormat Mask Format
 * @param unmaskCount Text length with no masked
 * @param maskCount Text length with mask
 */
constructor(private val maskName: String, val maskFormat: String, private val unmaskCount: Long, val maskCount: Long) {
    CPF("CPF", "###.###.###-##", 11, 14),
    CEP("CEP", "#####-###", 8, 9),
    CELLPHONE_MASK("Cell Phone Mask", "(##) #####-####", 11, 12),
    PHONE_MASK("Phone Mask", "### #### ####", 11, 13),
    CREDIT_CARD_NUMBER_MASK("Credit Card Number Mask", "#### #### #### ####", 16, 19),
    CREDIT_CARD_EXPIRATION_MASK("Credit Card Expiration Mask", "##/####", 6, 7),
    DATE("Date", "##/##/####", 8, 10)
}
