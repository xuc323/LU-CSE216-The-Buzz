var describe: any;
var it: any;
var expect: any;
var $: any;

describe("Tests of basic math functions", function () {
  it("Adding 1 should work", function () {
    var foo = 0;
    foo += 1;
    expect(foo).toEqual(1);
  });

  it("Subtracting 1 should work", function () {
    var foo = 0;
    foo -= 1;
    expect(foo).toEqual(-1);
  });
});

it("UI Test: Add Button Hides Listing and Cancel Button Shows Listing", function () {
  // click the button for showing the add button
  $("#showFormButton").click();
  // expect that the add form is not hidden
  expect($("#addElement").attr("style").indexOf("display: none;")).toEqual(-1);
  // expect that the element listing is hidden
  expect($("#showElements").attr("style").indexOf("display: none;")).toEqual(0);
  // click the cancel button to go back to the main page
  $("#addCancel").click();
  // expect that the element listing is not hidden
  expect($("#showElements").attr("style").indexOf("display: none;")).toEqual(
    -1
  );
  // expect that the add form is hidden
  expect($("#addElement").attr("style").indexOf("display: none;")).toEqual(0);
});

it("UI Test: Edit Button Brings Edit Form", function () {
  $(".editBtn:first").click();
  // expect that the edit form is not hidden
  expect($("#editElement").attr("style").indexOf("display: none;")).toEqual(-1);
  // expect that the element listing is hidden
  expect($("#showElements").attr("style").indexOf("display: none;")).toEqual(0);
  // reset the UI, so we don't mess up the next test
  $("#editCancel").click();
});
