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
    
      // Create an object of formData
      const formData = new FormData();
    
      // Update the formData object
      formData.append(
        "myFile",
        this.state.selectedFile,
        this.state.selectedFile.name
      );
    
      // Details of the uploaded file
      console.log(this.state.selectedFile);
    
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
            0 
        )
    }

}