// Phase 4
import React from 'react'
import {render, screen} from '@testing-library/react'
import userEvent from '@testing-library/user-event'

test('upload file', () => {
  const file = new File(['hello'], 'hello.png', {type: 'image/png'})

  render(
    <div>
      <label htmlFor="file-uploader">Pick File</label>
      <input id="file-uploader" type="file" />
    </div>,
  )
  const input = screen.getByLabelText(/Pick File/i)
  userEvent.upload(input, file)

  expect(input.files[0]).toStrictEqual(file)
  expect(input.files.item(0)).toStrictEqual(file)
  expect(input.files).toHaveLength(1)
})