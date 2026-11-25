import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.Keys as Keys

// Employee test data
String firstName = 'Alice'
String lastName = 'Martin'
String employeeId = 'QA-' + System.currentTimeMillis().toString().takeRight(7)

// Browser initialization
WebUI.openBrowser(null)

WebUI.navigateToUrl('https://opensource-demo.orangehrmlive.com/web/index.php/auth/login')

// Authentication
WebUI.setText(findTestObject('Page_OrangeHRM/input_Username'), 'Admin')

WebUI.setEncryptedText(findTestObject('Page_OrangeHRM/input_Password'), 'hUKwJTbofgPU9eVlw/CnDQ==')

WebUI.click(findTestObject('Page_OrangeHRM/button_Login'))

// Navigate to Add Employee
WebUI.click(findTestObject('Page_OrangeHRM/span_PIM'))

WebUI.click(findTestObject('Page_OrangeHRM/button_Add'))

// Fill employee information
WebUI.setText(findTestObject('Page_OrangeHRM/input_First Name'), firstName)

WebUI.setText(findTestObject('Page_OrangeHRM/input_Last Name'), lastName)

// Clear and replace the generated employee ID
WebUI.sendKeys(findTestObject('Page_OrangeHRM/Input_Employee_Id'), Keys.chord(Keys.CONTROL, 'a'))

WebUI.sendKeys(findTestObject('Page_OrangeHRM/Input_Employee_Id'), Keys.BACK_SPACE.toString())

WebUI.setText(findTestObject('Page_OrangeHRM/Input_Employee_Id'), employeeId)

// Save employee
WebUI.click(findTestObject('Page_OrangeHRM/button_Save'))

// Wait for employee details URL
String urlPattern = '.*/pim/viewPersonalDetails/empNumber/[0-9]+'

WebUI.waitForCondition({
    WebUI.getUrl().matches(urlPattern)
}, 15)

// Retrieve employee URL and internal ID
String employeeUrl = WebUI.getUrl()

WebUI.verifyMatch(employeeUrl, urlPattern, true)

String empNumber = employeeUrl.tokenize('/').last()

// Centralize employee data
Map employee = [
    firstName: firstName,
    lastName: lastName,
    employeeId: employeeId,
    empNumber: empNumber,
    url: employeeUrl
]

// Display employee information
println('[EMPLOYEE] First name: ' + employee.firstName)
println('[EMPLOYEE] Last name: ' + employee.lastName)
println('[EMPLOYEE] Employee ID: ' + employee.employeeId)
println('[EMPLOYEE] Internal number: ' + employee.empNumber)
println('[EMPLOYEE] URL: ' + employee.url)

// Verify employee details page
WebUI.verifyElementText(
	findTestObject('Page_OrangeHRM/h6_Personal Details'),
	'Personal Details'
)

// Verify employee first name
WebUI.verifyElementAttributeValue(
	findTestObject('Page_OrangeHRM/input_First Name'),
	'value',
	employee.firstName,
	10
)

// Verify employee last name
WebUI.verifyElementAttributeValue(
	findTestObject('Page_OrangeHRM/input_Last Name'),
	'value',
	employee.lastName,
	10
)

// Verify employee ID
WebUI.verifyElementAttributeValue(
	findTestObject('Page_OrangeHRM/Input_Employee_Id'),
	'value',
	employee.employeeId,
	10
)

// Close browser
WebUI.closeBrowser()