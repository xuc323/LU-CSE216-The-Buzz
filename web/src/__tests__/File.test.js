import React from "react"
import {render, screen} from '@testing-library/react'
import userEvent from '@testing-library/user-event'

// simulate a user uploading an image
test('upload a file', () => {
  const file = new File(['FSM Diagram'], 'FSM.jpg', {type: 'image/jpg'})

  render(
    <div>
      <button onClick="file-uploader">Pick File</button>
      <input id="file-uploader" type="file"/>
    </div>,
  )
  userEvent.upload(input, file)

  expect(input.files).toHaveLength(1)
});