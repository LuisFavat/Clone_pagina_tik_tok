import "./Button.css"

const Button = ({tag, accionClick}) => {
    return(
        <div className="ButtonWrapper">
            <div className="CustomButton" onClick={accionClick}>
                {tag}
            </div>
        </div>
    )
}

export default Button;