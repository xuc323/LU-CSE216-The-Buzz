import React from "react";
import axios from 'axios';

class File extends React.Component {
    constructor(props) {
        super(props);
    }

    state = {
        selectedFile: null 
    };

    onFileChange = event => {
    
      // Update the state
      this.setState({ selectedFile: event.target.files[0] });
    
    };

    // On file upload (click the upload button)
    onFileUpload = () => {
        const formData = new FormData(); // Create an object of formData
        formData.append("myFile", this.state.selectedFile, this.state.selectedFile.name); // Update the formData object
        axios.post('postgres://pkdkdvttlfzyfu:6368fa21b4ffd5891b25a4700c6ee3e85350bec637fd33aabd57879c6b97efe1@ec2-3-225-204-194.compute-1.amazonaws.com:5432/d7oeuj2oslhi4l', formData);
            then(res => {
                console.log(res); // Details of the uploaded file
            });

      // Request made to the backend api
      // Send formData object
      let fileField = document.getElementById("commentMessage");
      document.getElementById("fileSubmit").onclick = async function () {
            const url = "/messages/" + mId + "/files";
            //const url = "http://localhost:4567/messages" + mId + "/comments";
            try {
                const response = await fetch(url, {
                    method: "POST",
                    body: JSON.stringify({
                        mMessage: fileField.value
                    })
                });
                const json = await response.json();
                console.log(json);
                if (json.mStatus === "ok") {
                    window.location.reload(true);
                } else {
                    window.alert("Adding file failed.");
                }
            } catch (err) {
                console.log(err);
            }
        }
    };







    render() {
        return (
            <div className="File">
                <input
                style={{display: 'none'}}
                type='file'
                onChange={this.onFileChange}
                ref={fileInput => this.fileInput = fileInput}/>
                <button onClick={() => this.fileInput.click()}>Pick File</button>
                <button onClick={this.onFileUpload}>Upload</button>
            </div>
        )
    }
}