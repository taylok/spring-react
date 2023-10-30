export function getCurrency(number) {
  const formatter = new Intl.NumberFormat('en-UK', {
    style: 'currency',
    currency: 'GBP',});
  return formatter.format(number)
}